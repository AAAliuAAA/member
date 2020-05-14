package com.member.base.common.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CommonMapper<T> extends CommonDeleteMapper<T>,
        CommonInsertMapper<T>, CommonSelectMapper<T>, CommonUpdateMapper<T> {
    @Select("${selSql}")
    List<T> queryBySql(@Param("selSql") String selSql);

    @Select("${sql}")
    List<T> queryByParamBind(Map<String, Object> param);
}
