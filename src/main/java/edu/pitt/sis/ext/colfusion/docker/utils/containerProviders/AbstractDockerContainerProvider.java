package edu.pitt.sis.ext.colfusion.docker.utils.containerProviders;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.pitt.sis.exp.colfusion.utils.PairOf;
import edu.pitt.sis.ext.colfusion.docker.utils.ColfusionDockerClient;
import edu.pitt.sis.ext.colfusion.docker.utils.containers.AbstractDockerContainer;


public abstract class AbstractDockerContainerProvider<DockerContainer extends AbstractDockerContainer> {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	public static final String DOCKER_LATEST_TAG = "latest";
	
	protected final ColfusionDockerClient dockerClient;
	
	public AbstractDockerContainerProvider(final ColfusionDockerClient dockerClient) {
		this.dockerClient = dockerClient;
	}

	public DockerContainer createContainer() throws IOException {		
		return createContainer(DOCKER_LATEST_TAG);
	}
	
	public DockerContainer createContainer(final String tag) throws IOException {
		String actualTag = "".equals(tag) ? DOCKER_LATEST_TAG : tag;
		
		logger.info(String.format("Creating container for image '%s' and tag '%s'", getImageName(), actualTag));
		
		String containerId = dockerClient.createContainer(getImageName(), actualTag, getEnvVariables());
		
		return createContainerInternal(containerId, dockerClient);
	}
	
	public DockerContainer createAndStartContainer() throws Exception {
		return createAndStartContainer(DOCKER_LATEST_TAG);
	}
	
	public DockerContainer createAndStartContainer(final String tag) throws Exception {
		DockerContainer container = createContainer(tag);
		container.start();
		
		return container;
	}

	public abstract PairOf<String, String>[] getEnvVariables();

	public abstract String getImageName();

	protected abstract DockerContainer createContainerInternal(String containerId, ColfusionDockerClient dockerClient);
}
