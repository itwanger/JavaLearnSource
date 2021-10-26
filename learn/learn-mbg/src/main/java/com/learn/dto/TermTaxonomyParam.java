package com.learn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
public class TermTaxonomyParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")

    private Long termTaxonomyId;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "名称")
    @NotBlank(message="名称不能为空")
    private String name;

    @ApiModelProperty(value = "父栏目id")
    private Long parentId;

    @ApiModelProperty(value = "模板路径")
    @NotBlank(message="模板路径不能为空")
    private String tplPath;

    @ApiModelProperty(value = "内容模板路径")
    @NotBlank(message="内容模板路径不能为空")
    private String contentTplPath;

    @ApiModelProperty(value = "站点id")
    @NotNull(message="站点id不能为空")
    private Long siteId;


}
