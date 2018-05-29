package com.belling.admin.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.belling.base.model.PersistentObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class BizScope extends PersistentObject {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1106412532325860697L;

    /**
     * 业务范围名称
     */
    private String bizName;

    /**
     * 业务范围编号
     */
    private String bizValue;

    /**
     * 业务范围地址
     */
    private String bizAddr;

    /**
     * 业务范围联系人
     */
    private String bizContact;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUser;
}
