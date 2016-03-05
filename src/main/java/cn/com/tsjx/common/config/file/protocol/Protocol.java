package cn.com.tsjx.common.config.file.protocol;

import java.io.Closeable;

import cn.com.tsjx.common.config.file.FileConfigGroup;

import com.dangdang.config.service.exception.InvalidPathException;
import com.dangdang.config.service.file.FileLocation;

public interface Protocol extends Closeable {

	/**
	 * Read data of file
	 * 
	 * @param location
	 * @return
	 */
	byte[] read(FileLocation location) throws InvalidPathException;

	/**
	 * Register watcher for the file
	 * 
	 * @param location
	 * @param fileConfigGroup
	 */
	void watch(FileLocation location, FileConfigGroup fileConfigGroup, int index) throws InvalidPathException;

}