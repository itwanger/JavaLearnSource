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
 * 标签文章关系表
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PostTagRelation对象", description="标签文章关系表")
public class PostTagRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对应文章ID")
    @TableId(value = "object_id", type = IdType.AUTO)
    private Long objectId;

    @ApiModelProperty(value = "标签ID")
    private Long postTagId;

    @ApiModelProperty(value = "排序")
    private Integer termOrder;


}
