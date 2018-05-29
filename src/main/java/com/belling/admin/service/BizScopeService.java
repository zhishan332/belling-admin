package com.belling.admin.service;

import com.belling.admin.model.BizScope;
import com.belling.base.model.Pagination;
import com.belling.base.service.BaseService;

import java.sql.Timestamp;
import java.util.List;

public interface BizScopeService extends BaseService<BizScope, Integer> {


    Pagination<BizScope> findPaginationByParam(Integer userId,
                                               String bizName,
                                               String bizValue,
                                               String bizAddr,
                                               String bizContact,
                                               String createUser,
                                               Timestamp startTime,
                                               Timestamp endTime,
                                               Pagination<BizScope> p);


}
