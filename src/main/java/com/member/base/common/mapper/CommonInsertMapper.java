package com.member.base.common.mapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

public interface CommonInsertMapper<T> extends Marker,
        BaseInsertMapper<T>,
        InsertSelectiveMapper<T>{
}
