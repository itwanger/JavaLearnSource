package com.learn.service;

import com.learn.model.TermTaxonomy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.vo.TermTaxonomyTreeNode;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 栏目 服务类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
public interface ITermTaxonomyService extends IService<TermTaxonomy> {

    /**
     * 删除栏目 包含了逻辑判断
     * @param termTaxonomyId
     * @return
     */
    boolean removeTermTaxonomy(long termTaxonomyId);

    /**
     * 根据站点id和父栏目id获得所有子孙栏目节点
     * @param parentId 父栏目id
     * @param siteId 所属站点id
     * @return
     */
    List<TermTaxonomyTreeNode> getAllByParentId(Long parentId, long siteId);

    /**
     * 根据父栏目id获得直属子栏目
     * @param parentId 父栏目id，如果为null则返回null
     * @return
     */
    List<TermTaxonomyTreeNode> getChildrenByParentId(Long parentId);
}
