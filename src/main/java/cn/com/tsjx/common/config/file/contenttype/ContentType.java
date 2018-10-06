package cn.com.tsjx.common.config.file.contenttype;

import java.util.Map;

import com.dangdang.config.service.exception.InvalidPathException;

public interface ContentType {

	Map<String, String> resolve(byte[] data, String encoding) throws InvalidPathException;

}