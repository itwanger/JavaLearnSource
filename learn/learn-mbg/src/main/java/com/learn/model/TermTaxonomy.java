package com.learn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 栏目
 * </p>
 *
 * @author 石磊
 * @since 2021-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TermTaxonomy对象", description="栏目")
public class TermTaxonomy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "term_taxonomy_id", type = IdType.AUTO)
    private Long termTaxonomyId;

    @ApiModelProperty(value = "栏目名称")
    private String name;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "父栏目id")
    private Long parentId;

    @ApiModelProperty(value = "站点id")
    private Long siteId;

    @ApiModelProperty(value = "模板路径")
    private String tplPath;

    @TableField("create_user_id")
    @ApiModelProperty("创建用户id")
    private Long createUserId;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "内容模板模板路径")
    @TableField("content_tpl_path")
    private String contentTplPath;

}
