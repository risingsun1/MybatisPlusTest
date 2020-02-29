package com.jtexplorer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * article_文章表
 * </p>
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "arti_id", type = IdType.AUTO)
    private Long artiId;

    /**
     * 文章编号
     */
    private String artiNumber;

    /**
     * 编写人id
     */
    private Long artiUserId;

    /**
     * 编写人real_name
     */
    private String artiUserName;

    /**
     * 编写人nick_name
     */
    private String artiUserNickName;

    /**
     * 标题
     */
    private String artiTitle;

    /**
     * 封面
     */
    private String artiCover;

    /**
     * 商品在前台显示的微缩图片
     */
    private String artiThumb;

    /**
     * 类型：广告：advertising    关于我们：aboutUs   
     */
    private String artiType;

    /**
     * 连接地址
     */
    private String artiUrl;

    /**
     * 备注
     */
    private String artiRemark;

    /**
     * 状态:Y显示   N不显示
     */
    private String artiStatus;

    /**
     * 记录创建时间
     */
    private LocalDateTime artiDbCreateDate;

    /**
     * 记录上次修改时间
     */
    private LocalDateTime artiDbUpdateDate;

    /**
     * 简介
     */
    private String artiIntro;


}
