package cn.com.tsjx.sysOption.service;

import cn.com.tsjx.common.service.BaseService;
import cn.com.tsjx.sysOption.entity.Sysoption;

/**
 * 服务接口。
 */
public interface SysoptionService extends BaseService<Sysoption, Long> {

	/**
	 * 根据code取值
	 *
	 * @param code
	 * @return
	 */
	public String getVal(String code);
}
