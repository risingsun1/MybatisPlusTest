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
 * 
 * </p>
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户编号
     */
    private String userNumber;

    private String userNickname;

    private String userPhone;

    private String userPassword;

    private String userProvince;

    private String userCity;

    private String userCounty;

    private String userAddress;

    private String userAccount;

    private String userSalt;

    private String userRealName;

    private String userSex;

    private String userImage;

    private LocalDateTime userCreateTime;

    private LocalDateTime userUpdateTim;

    private LocalDateTime userBirthday;

    private String userRemark;

    private LocalDateTime userLastLoginTime;

    private String userLastLoginIp;


}
