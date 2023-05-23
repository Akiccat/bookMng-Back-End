package com.accenture.powerup.bookmng.entity;

import java.time.LocalDateTime;
/**
 * 实体类基类。
 * <p>包含创建日期，更新日期，删除FLAG</p>
 */
public class BaseEntity {
    // 创建时间
    private LocalDateTime createDateTime;
    // 更新时间
    private LocalDateTime updateDateTime;
    // 是否删除
    private Boolean deleteFlag;

    public BaseEntity() {
        LocalDateTime now = LocalDateTime.now();
        this.createDateTime = now;
        this.updateDateTime = now;
        this.deleteFlag = Boolean.FALSE;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
