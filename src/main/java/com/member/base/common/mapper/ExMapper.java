package com.member.base.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExMapper {
    @Select("${selSql}")
    List<Map<String,Object>> queryBySql(@Param("selSql") String selSql);

    @Select("${sql}")
    List<Map<String,Object>> queryByParamBind(Map<String, Object> param);
}
