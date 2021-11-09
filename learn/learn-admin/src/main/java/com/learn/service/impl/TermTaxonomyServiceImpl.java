package com.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.model.TermRelationships;
import com.learn.model.TermTaxonomy;
import com.learn.mapper.TermTaxonomyMapper;
import com.learn.service.ITermRelationshipsService;
import com.learn.service.ITermTaxonomyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.vo.TermTaxonomyTreeNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 栏目 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Service
public class TermTaxonomyServiceImpl extends ServiceImpl<TermTaxonomyMapper, TermTaxonomy> implements ITermTaxonomyService {

    @Autowired
    private ITermRelationshipsService termRelationshipsService;

    @Override
    public boolean removeTermTaxonomy(long termTaxonomyId) {
        QueryWrapper<TermRelationships> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("term_taxonomy_id", termTaxonomyId);
        int count = termRelationshipsService.count(queryWrapper);
        if (count > 0) {
            return false;
        }
        return this.removeById(termTaxonomyId);
    }

    @Override
    public List<TermTaxonomyTreeNode> getAllByParentId(Long parentId, long siteId) {
        int firstLevelParentId = 0;
        List<TermTaxonomyTreeNode> treeNodes = new ArrayList<>();
        QueryWrapper<TermTaxonomy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("site_id", siteId);
        List<TermTaxonomy> termTaxonomyList = this.list(queryWrapper);
        List<TermTaxonomyTreeNode> rootTreeNodes = new ArrayList<>();

        termTaxonomyList.forEach(item->{
            TermTaxonomyTreeNode treeNode = new TermTaxonomyTreeNode();
            BeanUtils.copyProperties(item,treeNode);
            treeNodes.add(treeNode) ;
        });

        if(parentId!=null){
            rootTreeNodes =  treeNodes.stream().filter(termTaxonomy -> parentId.equals(termTaxonomy.getParentId())).collect(Collectors.toList());
        }else {
            rootTreeNodes =  treeNodes.stream().filter(termTaxonomy -> termTaxonomy.getParentId() == firstLevelParentId).collect(Collectors.toList());
        }

        rootTreeNodes.forEach(node->{
            loopGetAll(node,treeNodes);
        });

        return rootTreeNodes;
    }

    @Override
    public List<TermTaxonomyTreeNode> getChildrenByParentId(Long parentId) {

        List<TermTaxonomyTreeNode> treeNodes = new ArrayList<>();
        QueryWrapper<TermTaxonomy> queryWrapper = new QueryWrapper<>();
        if(parentId != null) {
            queryWrapper.eq("parent_id", parentId);
        }
        else {
            return null;
        }
        List<TermTaxonomy> termTaxonomyList = this.list(queryWrapper);

        termTaxonomyList.forEach(item->{
            TermTaxonomyTreeNode treeNode = new TermTaxonomyTreeNode();
            BeanUtils.copyProperties(item,treeNode);
            treeNodes.add(treeNode) ;
        });

        return treeNodes;
    }

    private void loopGetAll( TermTaxonomyTreeNode rootTreeNode,List<TermTaxonomyTreeNode> treeNodes ){
        List<TermTaxonomyTreeNode> childrenList = treeNodes.stream().filter(termTaxonomy -> rootTreeNode.getTermTaxonomyId().longValue() == termTaxonomy.getParentId()).collect(Collectors.toList());
        if(childrenList.size() == 0){
            return;
        }
        rootTreeNode.setChildren(childrenList);
        childrenList.forEach(node->{
            loopGetAll(node,treeNodes);
        });
    }
}
