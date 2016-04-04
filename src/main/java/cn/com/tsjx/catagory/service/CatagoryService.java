package cn.com.tsjx.catagory.service;

import cn.com.tsjx.common.service.BaseService;
import cn.com.tsjx.catagory.entity.Catagory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 产品类别表服务接口。
 */
public interface CatagoryService extends BaseService<Catagory, Long> {

	public void uploadFileBuild(InputStream inputStream) throws Exception;
}
