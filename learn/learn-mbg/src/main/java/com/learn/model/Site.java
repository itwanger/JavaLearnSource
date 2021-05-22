package com.learn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String siteName;

    @TableField("SITE_DESC")
    private String siteDesc;

    @TableField("DOMAIN")
    private String domain;

    @TableField("TEL_NAME")
    private String telName;

    @TableField("STATIC_DIR")
    private String staticDir;


}
