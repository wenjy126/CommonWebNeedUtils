package com.netzoom.common.util;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * mongodb通用dao
 *
 * @author wenjy
 * @date 2019/12/20 13:48
 */
@Component
public class MongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加数据
     *
     * @param object    新对象
     * @param tableName 表名
     * @return 添加条数
     */
    public long insert(Object object, String tableName) {
        if (null == object) {
            return -1;
        }
        mongoTemplate.save(object, tableName);

        return 1;
    }

    /**
     * 根据条件修改一条数据
     *
     * @param conditions   条件
     * @param updateObject 更新字段
     * @param tableName    表名
     * @return 更新条数
     */
    public long updateOne(Object conditions, Object updateObject, String tableName) {
        Query query = new Query();
        Update update = new Update();

        try {
            Class conditionClazz = conditions.getClass();
            Field[] conditionFields = conditionClazz.getDeclaredFields();
            for (int i = 0; i < conditionFields.length; i++) {
                Field field = conditionFields[i];
                field.setAccessible(true);

                String key = field.getName();
                Object value = field.get(conditions);

                if (null != value) {
                    query.addCriteria(Criteria.where(key).is(value));
                }

            }

            Class updateClazz = updateObject.getClass();
            Field[] updateFields = updateClazz.getDeclaredFields();
            for (int i = 0; i < updateFields.length; i++) {
                Field field = updateFields[i];
                field.setAccessible(true);

                String key = field.getName();
                Object value = field.get(updateObject);

                if (null != value) {
                    update.set(key, value);
                }

            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, tableName);
        return updateResult.getModifiedCount();
    }

    /**
     * 根据条件更新多条数据
     *
     * @param conditions   条件
     * @param updateObject 更新字段
     * @param tableName    表名
     * @return 更新条数
     */
    public long updateAll(Object conditions, Object updateObject, String tableName) {
        Query query = new Query();
        Update update = new Update();

        try {
            Class conditionClazz = conditions.getClass();
            Field[] conditionFields = conditionClazz.getDeclaredFields();
            for (int i = 0; i < conditionFields.length; i++) {
                Field field = conditionFields[i];
                field.setAccessible(true);

                String key = field.getName();
                Object value = field.get(conditions);

                if (null != value) {
                    query.addCriteria(Criteria.where(key).is(value));
                }

            }

            Class updateClazz = updateObject.getClass();
            Field[] updateFields = updateClazz.getDeclaredFields();
            for (int i = 0; i < updateFields.length; i++) {
                Field field = updateFields[i];
                field.setAccessible(true);

                String key = field.getName();
                Object value = field.get(updateObject);

                if (null != value) {
                    update.set(key, value);
                }

            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, tableName);
        return updateResult.getModifiedCount();
    }

    /**
     * 删除数据
     *
     * @param conditions 条件
     * @param tableName  表名
     * @return 删除条数
     */
    public long delete(Object conditions, String tableName) {
        Query query = new Query();

        try {
            Class clazz = conditions.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);

                String key = f.getName();
                Object value = f.get(conditions);

                if (null != value) {
                    query.addCriteria(Criteria.where(key).is(value));
                }

            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        DeleteResult deleteResult = mongoTemplate.remove(query, tableName);
        return deleteResult.getDeletedCount();
    }

    /**
     * 根据条件查询一条数据
     *
     * @param conditions 条件
     * @param tableName  表名
     * @param <T>        泛型
     * @return T
     */
    public <T> T findOne(Object conditions, String tableName) {
        Query query = new Query();

        try {
            Class clazz = conditions.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);

                String key = f.getName();
                Object value = f.get(conditions);

                if (null != value) {
                    query.addCriteria(Criteria.where(key).is(value));
                }

            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) mongoTemplate.findOne(query, conditions.getClass(), tableName);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param conditions 条件
     * @param tableName  表名
     * @param <T>        泛型
     * @return List<T>
     */
    public <T> List<T> findList(Object conditions, String tableName) {
        Query query = new Query();

        try {
            Class clazz = conditions.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);

                String key = f.getName();
                Object value = f.get(conditions);

                if (null != value) {
                    query.addCriteria(Criteria.where(key).is(value));
                }

            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (List<T>) mongoTemplate.find(query, conditions.getClass(), tableName);
    }

    /**
     * 根据字节对象查询所有数据
     *
     * @param clazz     字节对象
     * @param tableName 表名
     * @param <T>       泛型
     * @return List<T>
     */
    public <T> List<T> findAll(Class clazz, String tableName) {
        return mongoTemplate.findAll(clazz, tableName);
    }

}
