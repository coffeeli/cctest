package dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAO {
	Log  LOG = LogFactory.getLog(BaseDAO.class);
	@Autowired
	private SqlSession sqlSession;

	public <T> T selectOne() {
		return this.sqlSession.selectOne(getParamString());
	};

	public <T> T selectOne(Object paramObject) {
		return this.sqlSession.selectOne(getParamString(), paramObject);
	};

	public <T> T selectOne(String paramString, Object paramObject) {
		return this.sqlSession.selectOne(getParamString(paramString),
				paramObject);
	};
	
	public <E> List<E> selectList() {
		return this.sqlSession.selectList(getParamString());
	};

	public <E> List<E> selectList(Object paramObject) {
		return this.sqlSession.selectList(getParamString(), paramObject);
	};

	public <E> List<E> selectList(String paramString, Object paramObject) {
		return this.sqlSession.selectList(getParamString(paramString),
				paramObject);
	};

	public int insert(Object param) {
		return sqlSession.insert(getParamString(), param);
	}

	public int insert(Object param, String mapperID) {
		return sqlSession.insert(getParamString(mapperID), param);
	}
	
	public int update() {
		return sqlSession.update(getParamString());
	}
	
	public int update(Object param) {
		return sqlSession.update(getParamString(), param);
	}

	public int update(Object param, String mapperID) {
		return sqlSession.update(getParamString(mapperID), param);
	}
	
	public int delete(Object param) {
		return sqlSession.delete(getParamString(), param);
	}

	public int delete(Object param, String mapperID) {
		return sqlSession.delete(getParamString(mapperID), param);
	}

	public void insertBatch(List<?> params){
		for(Object param: params){
			this.sqlSession.insert(getParamString(), param);
		}
	}
	
	private String getParamString() {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		StackTraceElement stack = stacks[3];
		String sqlID = stack.getClassName() + "." + stack.getMethodName();
		LOG.debug("sql id :"+sqlID);
		return sqlID;
	}

	private String getParamString(String mapperID) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		StackTraceElement stack = stacks[3];
		String sqlID = stack.getClassName() + "." + mapperID;
		LOG.debug("sql id :"+sqlID);
		return sqlID;
	}

}
