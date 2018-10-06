package cn.com.tsjx.common.config.file.protocol;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.dangdang.config.service.exception.InvalidPathException;
import com.dangdang.config.service.file.FileLocation;

public class FileProtocol extends LocalFileProtocol {

	@Override
	protected Path getPath(FileLocation location) throws InvalidPathException {
		return Paths.get(location.getFile());
	}

}