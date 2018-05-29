package com.belling.admin.mapper;

import com.belling.admin.model.BizScope;
import com.belling.base.mapper.BaseMapper;
import com.belling.base.model.Pagination;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface BizScopeMapper extends BaseMapper<BizScope, Integer> {


    List<BizScope> findPaginationByParam(@Param("bizName") String bizName,
                                         @Param("bizValue") String bizValue,
                                         @Param("bizAddr") String bizAddr,
                                         @Param("bizContact") String bizContact,
                                         @Param("createUser") String createUser,
                                         @Param("idList") List<Integer> idList,
                                         @Param("startTime") Timestamp startTime,
                                         @Param("endTime") Timestamp endTime,
                                         Pagination<BizScope> p);


    BizScope getByBizValue(@Param("bizValue") String bizValue);
}
