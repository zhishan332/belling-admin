package com.belling.admin.mapper;

import com.belling.admin.model.BizUserScope;
import com.belling.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BizUserScopeMapper extends BaseMapper<BizUserScope, Integer> {

    public List<BizUserScope> findByUserId(@Param("userId") Integer userId);

    public int deleteByBizScopeIds(@Param("idList") List<Integer> idList);

    public int deleteByUserIds(@Param("idList") List<Integer> idList);

    public int deleteForChangeUser(@Param("userId") Integer userId);
}
