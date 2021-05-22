package com.learn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 分类属性
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Termmeta对象", description="分类属性")
public class Termmeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "meta_id", type = IdType.AUTO)
    private Long metaId;

    @ApiModelProperty(value = "分类id")
    private Long termTaxonomyId;

    @ApiModelProperty(value = "分类key")
    private String metaKey;

    @ApiModelProperty(value = "分类值")
    private String metaValue;


}
