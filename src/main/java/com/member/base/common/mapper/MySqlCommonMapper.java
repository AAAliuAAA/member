package com.member.base.common.mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MySqlCommonMapper<T> extends CommonMapper<T> , MySqlMapper<T> {
}
