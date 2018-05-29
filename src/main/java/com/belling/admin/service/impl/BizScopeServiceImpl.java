package com.belling.admin.service.impl;

import com.belling.admin.mapper.BizScopeMapper;
import com.belling.admin.mapper.BizUserScopeMapper;
import com.belling.admin.model.BizScope;
import com.belling.admin.model.BizUserScope;
import com.belling.admin.service.BizScopeService;
import com.belling.base.model.Pagination;
import com.belling.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("bizScopeService")
public class BizScopeServiceImpl extends BaseServiceImpl<BizScopeMapper, BizScope, Integer> implements BizScopeService {


    @Autowired
    private BizUserScopeMapper bizUserScopeMapper;


    @Override
    public Pagination<BizScope> findPaginationByParam(Integer userId,
                                                      String bizName,
                                                      String bizValue,
                                                      String bizAddr,
                                                      String bizContact,
                                                      String createUser,
                                                      Timestamp startTime,
                                                      Timestamp endTime,
                                                      Pagination<BizScope> p) {

        List<BizUserScope> scopes = bizUserScopeMapper.findByUserId(userId);
        List<Integer> idList = new ArrayList<>();
        if (scopes != null && !scopes.isEmpty()) {
            for (BizUserScope userScope : scopes) {
                idList.add(userScope.getBizScopeId());
            }
        }
        mapper.findPaginationByParam(bizName, bizValue, bizAddr, bizContact, createUser, idList, startTime, endTime, p);
        return p;
    }

    @Override
    @Autowired
    public void setMapper(BizScopeMapper mapper) {
        this.mapper = mapper;
    }
}
