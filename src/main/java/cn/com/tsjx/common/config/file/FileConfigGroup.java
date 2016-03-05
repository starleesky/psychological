package cn.com.tsjx.common.config.file;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.tsjx.common.config.file.protocol.Protocol;
import cn.com.tsjx.common.config.file.protocol.Protocols;

import com.dangdang.config.service.ConfigGroup;
import com.dangdang.config.service.exception.InvalidPathException;
import com.dangdang.config.service.file.FileConfigProfile;
import com.dangdang.config.service.file.FileLocation;
import com.dangdang.config.service.file.contenttype.ContentType;
import com.dangdang.config.service.file.contenttype.ContentTypes;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

public class FileConfigGroup extends com.dangdang.config.service.GeneralConfigGroup {

	private static final long serialVersionUID = 1L;

	private FileConfigProfile configProfile;

	private FileLocation[] locations;

	private Protocol[] protocolBeans;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileConfigGroup.class);

	public FileConfigGroup(FileConfigProfile configProfile, String[] locations) {
		this(null, configProfile, locations);
	}

	public FileConfigGroup(ConfigGroup internalConfigGroup, FileConfigProfile configProfile, String[] locations) {
		super(internalConfigGroup);
		this.configProfile = configProfile;
		if (locations != null && locations.length > 0) {
			this.locations = new FileLocation[locations.length];
			this.protocolBeans = new Protocol[locations.length];
			for (int i = 0; i < locations.length; i++) {
				this.locations[i] = FileLocation.fromLocation(Preconditions.checkNotNull(locations[i], "Location cannot be null."));
				initConfigs(i);
				try {
					protocolBeans[i].watch(this.locations[0], this, i);
				} catch (InvalidPathException e) {
					throw Throwables.propagate(e);
				}
			}
		}
	}

	protected void initConfigs(int index) {
		LOGGER.debug("Loading file: {}", locations[index]);
		try {
			protocolBeans[index] = Protocols.getInstance().get(locations[index].getProtocol()).newInstance();

			ContentType contentTypeBean = ContentTypes.getInstance().get(configProfile.getContentType()).newInstance();
			putAll(contentTypeBean.resolve(protocolBeans[index].read(locations[index]), configProfile.getFileEncoding()));
		} catch (InvalidPathException e) {
			throw Throwables.propagate(e);
		} catch (InstantiationException e) {
			throw Throwables.propagate(e);
		} catch (IllegalAccessException e) {
			throw Throwables.propagate(e);
		}
	}

	@Override
	public void close() throws IOException {
		if (protocolBeans != null && protocolBeans.length > 0) {
			for (int i = 0; i < protocolBeans.length; i++) {
				if (protocolBeans[i] != null) {
					protocolBeans[i].close();
				}
			}
		}
	}
}
