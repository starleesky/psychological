package cn.com.tsjx.common.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import cn.com.tsjx.common.constants.Symbol;
import cn.com.tsjx.common.util.lang.StringUtil;
import cn.com.tsjx.common.web.model.DataGrid;
import cn.com.tsjx.common.web.model.Page;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;



/**
 * MyBatis的数据访问通用方法定义。
 * @author andy
 */
public abstract class SqlSessionDao {

	@Resource
	private SqlSession sqlSession;


	/**
	 * 根据处理器标识返回处理器全名称。
	 * @param id
	 * @return
	 */
	protected String buildStatNameById(String id) {
		String namespace = this.getClass().getName();
		return namespace + Symbol.DOT + id;
	}
	
	/**
	 * 根据处理器标识返回处理器全名称。
	 * @param id
	 * @return
	 */
	protected String buildStatNameById() {
		String namespace = this.getClass().getName();
		return namespace + Symbol.DOT + getMethodName();
	}

	/**
	 * 获取当前方法的名称。
	 * @return
	 */
	protected String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	protected SqlSession getSqlSession() {
		return this.sqlSession;
	}

	// ~ 以下方法为代理SqlSessionTemplate类中的方法，与原方法不同的是它传为的是处理器的ID，不需要命名空间。
	protected <E> E selectOne(String id, Object parameter) {
		String statement = this.buildStatNameById(id);
		return sqlSession.<E> selectOne(statement, parameter);
	}
	
	protected <E> E selectOne(Object parameter) {
		String statement = this.buildStatNameById();
		return sqlSession.<E> selectOne(statement, parameter);
	}

	protected <E> List<E> selectList(String id, Object parameter) {
		String statement = this.buildStatNameById(id);
		return sqlSession.<E> selectList(statement, parameter);
	}
	
	protected <E> List<E> selectList(Object parameter) {
		String statement = this.buildStatNameById();
		return sqlSession.<E> selectList(statement, parameter);
	}

	protected <E> Pager<E> selectPage(String id, Params params, Pager<E> pager) {	    
		params.add("page", pager);
		params.add("entity",pager.getEntity());
		if(StringUtil.isNotBlank(pager.getProperty())&&StringUtil.isNotBlank(pager.getKeyword())){
		    params.add("entity."+pager.getProperty(),pager.getKeyword());
		}
		Object result = this.selectOne(id + "_count", params);
		List<E> rows = this.<E> selectList(id + "_datas", params);
		if (result instanceof Number) {
			Number total = (Number) result;
			pager.setTotalCount(total.intValue());
		}else{
		    pager.setTotalCount(0);
		}		
		pager.setItems(rows);		
		return pager;
	}

	private Integer safeParseInt(Object value) {
		if (value instanceof BigDecimal) {
			return ((BigDecimal) value).intValue();
		} else {
			return ((Integer) value).intValue();
		}
	}

	protected int insert(String id, Object parameter) {
		String statement = this.buildStatNameById(id);
		return sqlSession.insert(statement, parameter);
	}
	
	protected int insert(Object parameter) {
		String statement = this.buildStatNameById();
		return sqlSession.insert(statement, parameter);
	}

	protected int update(String id, Object parameter) {
		String statement = this.buildStatNameById(id);
		return sqlSession.update(statement, parameter);
	}
	
	protected int update(Object parameter) {
		String statement = this.buildStatNameById();
		return sqlSession.update(statement, parameter);
	}

	protected int delete(String id, Object parameter) {
		String statement = this.buildStatNameById(id);
		return sqlSession.delete(statement, parameter);
	}
	
	protected int delete(Object parameter) {
		String statement = this.buildStatNameById();
		return sqlSession.delete(statement, parameter);
	}
	
	public String getDataBaseId(){
		return sqlSession.getConfiguration().getDatabaseId();
	}
}
