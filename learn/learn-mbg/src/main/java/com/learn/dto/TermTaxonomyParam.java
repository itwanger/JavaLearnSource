package com.learn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message="ID不能为空")
    private Long termTaxonomyId;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "父栏目id")
    private Long parentId;



}
