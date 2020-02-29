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
 * 角色表role
 * </p>
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 角色状态
     */
    private String roleStatus;

    /**
     * 描述
     */
    private String roleDescription;

    /**
     * 备注
     */
    private String roleRemark;

    /**
     * 记录创建日期时间
     */
    private LocalDateTime roleDbCreateDate;

    /**
     * 记录最后一次修改日期时间
     */
    private LocalDateTime roleDbUpdateDate;


}
