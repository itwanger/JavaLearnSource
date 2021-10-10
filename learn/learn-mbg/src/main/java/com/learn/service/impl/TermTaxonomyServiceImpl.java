package com.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.model.TermRelationships;
import com.learn.model.TermTaxonomy;
import com.learn.mapper.TermTaxonomyMapper;
import com.learn.service.ITermRelationshipsService;
import com.learn.service.ITermTaxonomyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return  this.removeById(termTaxonomyId);
    }
}
