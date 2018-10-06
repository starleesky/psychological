package cn.com.tsjx.common.dao;

import java.util.Collection;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;

import cn.com.tsjx.common.bean.entity.DeletedEntity;
import cn.com.tsjx.common.bean.entity.Entity;
import cn.com.tsjx.common.bean.entity.GroupEntity;
import cn.com.tsjx.common.bean.entity.OperationEntity;
import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.web.client.UserHolder;
import cn.com.tsjx.common.web.model.Params;



/**
 * MyBatis会话增删改查拦截器。
 * @author liwei
 */
public class SqlSessionInterceptor implements MethodInterceptor {

	/**
	 * 默认只拦截名称以insert、update、delete、select开始的方法。
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		
		if (methodName.startsWith("insert")) {
			return this.invokeInsert(invocation);
		} else if (methodName.startsWith("update")) {
			return this.invokeUpdate(invocation);
		} else if (methodName.startsWith("delete")) {
			return this.invokeDelete(invocation);
		} else if (methodName.startsWith("select")) {
			return this.invokeSelect(invocation);
		} else {
			return invocation.proceed();
		}
	}

	/**
	 * 插入方法拦截。<br>
	 * 取第二个方法参数，针对该参数时行数据修改
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	private Object invokeInsert(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		if (args.length > 1) {
			Object object = args[1];
			if (object instanceof Entity) {
				Entity<?> entity = (Entity<?>) object;
				this.insertEntitySet(entity);
			} else if (object instanceof Collection<?>) {
				Collection<?> coll = (Collection<?>) object;
				for (Object item : coll) {
					Entity<?> entity = (Entity<?>) item;
					this.insertEntitySet(entity);
				}
			}
		}
		return invocation.proceed();
	}

	/**
	 * 待插入实体修改。
	 * @param entity
	 */
	private void insertEntitySet(Entity<?> entity) {
		if (UserHolder.getUser() != null && entity instanceof OperationEntity) {
			OperationEntity operationEntity = (OperationEntity) entity;
			String userName = UserHolder.getName();
			Date now = UserHolder.getCurrentDate();
			operationEntity.setCreateBy(userName);
			operationEntity.setCreateTime(now);
			operationEntity.setModifyBy(userName);
			operationEntity.setModifyTime(now);
		}
		if (UserHolder.getUser() != null && entity instanceof GroupEntity) {
			GroupEntity corpGroupEntity = (GroupEntity) entity;
			if (StringUtils.isBlank(corpGroupEntity.getCorpCode())) {
				corpGroupEntity.setCorpCode(UserHolder.getCorpCode());
			}
			if (StringUtils.isBlank(corpGroupEntity.getCorpGroupCode())) {
				corpGroupEntity.setCorpGroupCode(UserHolder.getCorpGroupCode());
			}
		}
		if (entity instanceof DeletedEntity) {
			((DeletedEntity) entity).setDeleted(Deleted.NO.value);
		}
	}

	/**
	 * 更新方法拦截。<br>
	 * 取第二个方法参数，针对该参数时行数据修改
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	private Object invokeUpdate(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		if (args.length > 1) {
			Object object = args[1];
			if (object instanceof Entity) {
				Entity<?> entity = (Entity<?>) object;
				this.updateEntitySet(entity);
			} else if (object instanceof Collection<?>) {
				Collection<?> coll = (Collection<?>) object;
				for (Object item : coll) {
					Entity<?> entity = (Entity<?>) item;
					this.updateEntitySet(entity);
				}
			}
		}
		return invocation.proceed();
	}

	/**
	 * 待更新实体修改。
	 * @param entity
	 */
	private void updateEntitySet(Entity<?> entity) {
		if (UserHolder.getUser() != null && entity instanceof OperationEntity) {
			OperationEntity operationEntity = (OperationEntity) entity;
			operationEntity.setModifyBy(UserHolder.getName());
			operationEntity.setModifyTime(UserHolder.getCurrentDate());
		}
	}

	/**
	 * 删除方法拦截。<br>
	 * 取第二个方法参数，针对该参数时行数据修改
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	private Object invokeDelete(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		if (args.length > 1) {
			Object object = args[1];
			if (object instanceof Params) {
				Params params = (Params) object;
				params.addDeleted(Deleted.YES);
				if (UserHolder.getUser() != null) {
					params.addModifyBy(UserHolder.getName(), UserHolder.getCurrentDate());
					params.addCorpAndGroupCode(UserHolder.getCorpCode(), UserHolder.getCorpGroupCode());
				}
			}
		}
		return invocation.proceed();
	}

	/**
	 * 查询方法拦截。<br>
	 * 取第二个方法参数，针对该参数时行数据修改
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	private Object invokeSelect(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		if (args.length > 1) {
			Object object = args[1];
			if (object instanceof Params) {
				Params params = (Params) object;
				params.addDeleted(Deleted.NO);
				if (UserHolder.getUser() != null) {
					params.addCorpAndGroupCode(UserHolder.getCorpCode(), UserHolder.getCorpGroupCode());
				}
			} else if (object instanceof Entity) {
				Entity<?> entity = (Entity<?>) object;
				if (UserHolder.getUser() != null && entity instanceof GroupEntity) {
					GroupEntity corpGroupEntity = (GroupEntity) entity;
					if (StringUtils.isBlank(corpGroupEntity.getCorpCode())) {
						corpGroupEntity.setCorpCode(UserHolder.getCorpCode());
					}
					if (StringUtils.isBlank(corpGroupEntity.getCorpGroupCode())) {
						corpGroupEntity.setCorpGroupCode(UserHolder.getCorpGroupCode());
					}
				}
				if (entity instanceof DeletedEntity) {
					((DeletedEntity) entity).setDeleted(Deleted.NO.value);
				}
			}
		}
		return invocation.proceed();
	}
}
