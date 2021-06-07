package com.learn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Site对象", description="站点")
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SITE_ID", type = IdType.AUTO)
    private Long siteId;

    @TableField("SITE_NAME")
    @ApiModelProperty("站点名称")
    @NotBlank(message = "站点名称不能为空")
    private String siteName;

    @TableField("SITE_DESC")
    @ApiModelProperty("站点介绍")
    private String siteDesc;

    @TableField("DOMAIN")
    @NotBlank(message = "站点名称不能为空")
    @ApiModelProperty("站点名称")
    private String domain;

    @TableField("TEL_NAME")
    @NotBlank(message = "模板方案不能为空")
    @ApiModelProperty("模板方案")
    private String telName;

    @TableField("STATIC_DIR")
    @ApiModelProperty("静态目录")
    private String staticDir;


}
