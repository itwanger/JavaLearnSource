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


    List<TermTaxonomyTreeNode> getAllByParentId(Long parentId, long siteId);
}
