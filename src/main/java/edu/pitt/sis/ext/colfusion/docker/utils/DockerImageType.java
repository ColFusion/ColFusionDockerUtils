package edu.pitt.sis.ext.colfusion.docker.utils;

import edu.pitt.sis.ext.colfusion.docker.utils.containerProviders.AbstractDockerContainerProvider;
import edu.pitt.sis.ext.colfusion.docker.utils.containerProviders.MySQLContainerProvider;

public enum DockerImageType {
	MYSQL_IMAGE(MySQLContainerProvider.class);
	
	Class<? extends AbstractDockerContainerProvider<?>> containerProviderClazz;
	
	DockerImageType(final Class<? extends AbstractDockerContainerProvider<?>> containerProviderClazz) {
		this.containerProviderClazz = containerProviderClazz;
	}

	public Class<? extends AbstractDockerContainerProvider<?>> getContainerProviderClass() {
		return containerProviderClazz;
	}
}
