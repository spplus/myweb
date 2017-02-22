package qc.dm.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import qc.com.log4j.HpLogger;

/**
 * Mybatis基类
 */
@SuppressWarnings("unchecked")
public class MybatisDao extends DynamicSqlSessionDaoSupport{
	public  int insert(String key, Object object) {
		return getSqlSession().insert(key, object);
	}
	
	public  int update(String key,Object Object){
		return getSqlSession().update(key, Object);
	}
	
	public  int delete(String key, Serializable id) {
		return getSqlSession().delete(key, id);
	}

	public  int delete(String key, Object object) {
		return getSqlSession().delete(key, object);
	}
	
	public  <T> T get(String key, Object params) {
		return (T) getSqlSession().selectOne(key, params);
	}
	
	public  <T> List<T> getList(String key) {
		return getSqlSession().selectList(key);
	}
	
	public  <T> List<T> getList(String key, Object params) {
		return getSqlSession().selectList(key, params);
	}
	
	public boolean execute(String sql){
		boolean rs = true;
		try {
			rs = getSqlSession().getConnection().createStatement().execute(sql);
		} catch (SQLException e) {
			HpLogger.error("MybatisDao execute执行异常！",e);
		} 
		return rs;
	}

}
