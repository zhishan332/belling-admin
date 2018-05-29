package com.belling.admin.model;

import com.belling.base.model.PersistentObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员角色映射
 * 
 * @author Sunny
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizUserScope extends PersistentObject {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 4942358338145288018L;

	/** 管理员ID */
	private Integer userId;
	
	/** 业务区ID */
	private Integer bizScopeId;
}
