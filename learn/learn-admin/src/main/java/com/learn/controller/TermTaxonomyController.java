package com.learn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.dto.TermTaxonomyParam;
import com.learn.model.TermTaxonomy;
import com.learn.model.Users;
import com.learn.service.ITermTaxonomyService;
import com.learn.vo.TermTaxonomyTreeNode;
import com.learn.webapi.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 栏目 前端控制器
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Controller
@Api(tags = "栏目")
@RequestMapping("/termTaxonomy")
public class TermTaxonomyController {
    @Autowired
    private ITermTaxonomyService termTaxonomyService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加栏目")
    public ResultObject<String> insert(@Valid TermTaxonomy termTaxonomy) {
        return ResultObject.success(termTaxonomyService.save(termTaxonomy) ? "保存成功" : "保存失败");
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据id获取栏目")
    public ResultObject<TermTaxonomy> getById(long termTaxonomyId) {
        return ResultObject.success(termTaxonomyService.getById(termTaxonomyId));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更新")
    public ResultObject<String> update(@Valid TermTaxonomyParam param) {
        TermTaxonomy termTaxonomy = termTaxonomyService.getById(param.getTermTaxonomyId());
        BeanUtils.copyProperties(param, termTaxonomy);
        return ResultObject.success(termTaxonomyService.updateById(termTaxonomy) ? "更新成功" : "更新失败");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("删除")
    public ResultObject<String> delete(long termTaxonomyId) {
        return ResultObject.success(termTaxonomyService.removeTermTaxonomy(termTaxonomyId) ? "删除成功" : "删除失败");
    }

    @RequestMapping(value = "/queryPageable", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询")
    public ResultObject<Map<String, Object>> queryPageable(@RequestParam long pageSize, @RequestParam long page, @RequestParam long siteId) {
        Map<String, Object> map = new HashMap<>();
        Page<TermTaxonomy> termTaxonomyPage = new Page<>(page, pageSize);
        QueryWrapper<TermTaxonomy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("site_id", siteId);
        IPage<TermTaxonomy> termTaxonomyIPage = termTaxonomyService.page(termTaxonomyPage, queryWrapper);
        map.put("items", termTaxonomyIPage.getRecords());
        map.put("total", termTaxonomyIPage.getTotal());
        return ResultObject.success(map);
    }

    @RequestMapping(value = "/getPyParentId", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据父栏目id和站点id查询所有下级子孙栏目")
    public ResultObject<List<TermTaxonomyTreeNode>> getPyParentId(Long parentId, @RequestParam long siteId) {
        return ResultObject.success(termTaxonomyService.getAllByParentId(parentId, siteId));
    }

    @RequestMapping(value = "/getNextLevelPyParentId", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据父栏目id查询直属子栏目")
    public ResultObject<List<TermTaxonomyTreeNode>> getNextLevelPyParentId(Long parentId) {
        return ResultObject.success(termTaxonomyService.getChildrenByParentId(parentId));
    }
}

