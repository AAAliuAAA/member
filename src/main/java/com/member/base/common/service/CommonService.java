package com.member.base.common.service;


import com.member.base.common.mapper.CommonMapper;
import com.member.base.common.mapper.ExMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

public abstract class CommonService<E> {
    @Autowired
    CommonMapper<E> mapper;

  /*  @Autowired
    ExMapper exMapper;*/

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /*public List<Map<String,Object>> selectMapBySql(String sql){
        return exMapper.queryBySql(sql);
    };*/


    /**
     * 自己会生成自增主键
     * @param entity
     * @return
     */
    public int insert(E entity) {
        return mapper.insert(entity);
    }

    /**
     * 根据主键修改，传入的实体类必须包含主键id
     * @param entity
     * @return
     */
    public int updateByPrimaryKey(E entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 传入的实体类，包含主键，如果数据库有记录则更新，没有记录就插入
     * @param entity
     * @return
     */
    public int saveByPrimaryKey(E entity) {
        boolean exists = this.existsWithPrimaryKey(entity);

        if (exists) {
            return this.updateByPrimaryKey(entity);
        } else {
            return this.insert(entity);
        }
    }

    /**
     * 根据主键删数据
     * @param key
     * @return
     */
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    /**
     * 根据条件删，一次可能删多行
     * @param entity
     * @return
     */
    public int delete(E entity) {
        return mapper.delete(entity);
    }

    /**
     * 根据主键查，用处不大
     * @param key
     * @return
     */
    public E selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 根据实体类信息查结果集，一次返回多条
     * @param e
     * @return
     */
    public List<E> select(E e) {
        return mapper.select(e);
    }

    /**
     * 根据主键判断数据库是否存在记录
     * @param key
     * @return
     */
    public boolean existsWithPrimaryKey(Object key) {
        return mapper.existsWithPrimaryKey(key);
    }

    /**
     * 根据自定义sql查，select * 开头，容易出现sql注入的问题
     * @param s
     * @return
     */
    public List<E> selectBySql(String s) {
        return mapper.queryBySql(s);
    }

    /**
     * 类似于根据SQL查，sql条件转化成 example对象，可以防止sql注入，但灵活性没有自定义sql好
     * @param s
     * @return
     */
    public List<E> selectByExample(Example s) {
        return mapper.selectByExample(s);
    }


    /**
     * 自定义sql绑定变量，推荐使用
     * 用法：map中必须放 一对kv  key:"sql"，value:预编译语句
     * 需要传参的地方用#{param} 例如： code = #{code}
     * 然后在map中放kv
     * @param param
     * @return
     */
    public List<E> queryByParamBind(Map<String,Object> param) {
        return mapper.queryByParamBind(param);
    }



}
