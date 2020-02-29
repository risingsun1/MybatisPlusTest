package com.jtexplorer.entity.dto;

import com.jtexplorer.entity.enums.RequestEnum;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * JsonResult class
 *
 * @author 苏友朋
 * @date 2019/03/01 09:03
 */
@Data
@ToString
public class JsonResult {
    private boolean success;
    private long totalSize;
    private String tip;
    private String failReason;
    private List root;
    private BigDecimal amount;

    public void buildNew(boolean success, long totalSize, String tip, String failReason, List root) {
        this.tip = tip;
        this.failReason = failReason;
        this.root = root;
        this.success = success;
        this.totalSize = totalSize;
    }

    public void buildFalseNew(String tip, String failReason) {
        this.tip = tip;
        this.failReason = failReason;
        this.success = false;
    }

    public void buildTrueNew(long totalSize, List root) {
        this.root = root;
        this.success = true;
        this.totalSize = totalSize;
    }

    public void buildTrueNew() {
        this.success = true;
    }

    public void buildFalseNew(RequestEnum requestEnum) {
        this.tip = requestEnum.getCode();
        this.failReason = requestEnum.getMeaning();
        this.success = false;
    }

    public void buildFalseNew(RequestEnum requestEnum, String reason) {
        this.tip = requestEnum.getCode();
        this.failReason = requestEnum.getMeaning() + reason;
        this.success = false;
    }
}
