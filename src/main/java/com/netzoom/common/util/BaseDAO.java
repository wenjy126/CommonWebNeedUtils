package com.netzoom.common.util;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by TanzJ on 2018/12/12.
 *
 * @author tanzj
 * 通用DAO类
 */
@Component
public class BaseDAO extends SqlSessionDaoSupport {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private SqlSessionFactory sqlSessionFactory;

    public BaseDAO() {
    }

    /**
     * 根据Key删除记录
     *
     * @param key 删除的key
     * @return int　result
     */
    public int delete(Object key) throws DataAccessException {
        if (key == null) {
            return -1;
        } else if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return -1;
        } else {
            return this.getSqlSession().delete((String) key);
        }
    }

    /**
     * 根据key和对象删除记录
     *
     * @param key 删除的key
     * @param obj 删除的对象
     * @return int result
     */
    public int delete(Object key, Object obj) throws DataAccessException {
        if (obj == null) {
            return this.delete(key);
        } else if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return -1;
        } else {
            return this.getSqlSession().delete((String) key, obj);
        }
    }

    /**
     * 插入方法
     *
     * @param key String key
     * @return int result
     */
    public int insert(Object key) throws DataAccessException {
        if (key == null) {
            return -1;
        } else if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return -1;
        } else {
            return this.getSqlSession().insert((String) key);
        }
    }

    /**
     * 根据key和对象插入记录
     *
     * @param key String key
     * @param obj 存储的对象
     * @return int result
     */
    public int insert(Object key, Object obj) throws DataAccessException {
        if (obj == null) {
            return this.insert(key);
        } else if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return -1;
        } else {
            return this.getSqlSession().insert((String) key, obj);
        }
    }

    /**
     * 根据key查询记录，以List的形式返回
     *
     * @param key 查询的key
     * @param <T> 泛型对象
     * @return List
     */
    public <T> List<T> queryForList(Object key) throws DataAccessException {
        if (key == null) {
            return null;
        } else if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return null;
        } else {
            return this.getSqlSession().selectList((String) key);
        }
    }

    /**
     * 以key和对象的形式查询记录并以List的形式返回
     *
     * @param key 查询的key
     * @param obj 查询对象
     * @param <T> 泛型
     * @return list
     */
    public <T> List<T> queryForList(Object key, Object obj) throws DataAccessException {
        if (obj == null) {
            return this.queryForList(key);
        } else if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return null;
        } else {
            return this.getSqlSession().selectList((String) key, obj);
        }
    }

    /**
     * 查询一条记录
     *
     * @param key 查询的key
     * @return Object
     */
    private Object selectOne(Object key) throws DataAccessException {
        if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return null;
        } else {
            return this.getSqlSession().selectOne((String) key);
        }
    }

    /**
     * 根据key和对象查询一条记录
     *
     * @param key 查询的key
     * @param obj 对象
     * @return Object
     */
    private Object selectOne(Object key, Object obj) throws DataAccessException {
        if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return null;
        } else {
            return this.getSqlSession().selectOne((String) key, obj);
        }
    }

    /**
     * 通过key和object查询记录并以对象的形式返回
     *
     * @param key 查询的key
     * @param obj 查询的object
     * @param <T> 泛型
     * @return <T>  泛型
     */
    public <T> T queryForObject(Object key, Object obj) throws DataAccessException {
        Object result = this.selectOne(key, obj);
        return result == null ? null : (T) result;
    }

    /**
     * 以key的形式查询一条记录，并以泛型对象的形式返回
     *
     * @param key 查询的key
     * @param <T> 泛型
     * @return <T> 泛型
     */
    public <T> T queryForObject(Object key) throws DataAccessException {
        Object result = this.selectOne(key);
        return result == null ? null : (T) result;
    }

    /**
     * 根据key的形式更新一条记录
     *
     * @param key 更新的key
     * @return Integer -1错误 1成功
     */
    public Integer update(Object key) throws DataAccessException {
        if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return -1;
        } else {
            return this.getSqlSession().update((String) key);
        }
    }

    /**
     * 以key和对象的形式更新记录
     *
     * @param key 更新的key
     * @param obj 更新的对象
     * @return Integer -1错误 1成功
     */
    public Integer update(Object key, Object obj) throws DataAccessException {
        if (!(key instanceof String)) {
            this.log.info("【MyBatisDao】错误信息 : 参数不匹配");
            return null;
        } else {
            return this.getSqlSession().update((String) key, obj);
        }
    }

    /**
     * 查询记录并以map的形式返回
     *
     * @param key 查询的key
     * @param parameterObject
     * @param keyProperty
     * @return Map
     */
    public Map queryForMap(Object key, Object parameterObject, Object keyProperty) throws DataAccessException {
        return this.getSqlSession().selectMap((String) key, parameterObject, (String) keyProperty);
    }

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
