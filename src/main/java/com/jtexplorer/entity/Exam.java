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
 * exam_考试表
 * </p>
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Exam implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "exam_id", type = IdType.AUTO)
    private Long examId;

    /**
     * 年份
     */
    private String examYear;

    /**
     * 名字
     */
    private String examName;

    /**
     * 备注
     */
    private String examRemark;

    /**
     * 状态
     */
    private String examStatus;

    /**
     * 记录创建时间
     */
    private LocalDateTime examDbCreateDate;

    /**
     * 记录上次修改时间
     */
    private LocalDateTime examDbUpdateDate;


}
