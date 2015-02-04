/**
 * 
 */
package edu.pitt.sis.ext.colfusion.docker.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;

import edu.pitt.sis.exp.colfusion.utils.UnitTestBase;
import edu.pitt.sis.ext.colfusion.docker.utils.containers.AbstractDockerContainer;

/**
 * @author Evgeny
 *
 */
public class DockerTestBase extends UnitTestBase {
	private static final List<AbstractDockerContainer> containers = new ArrayList<AbstractDockerContainer>();
	
	protected static AbstractDockerContainer createAndStartContainer(final DockerImageType dockerImageType) throws Exception {
		AbstractDockerContainer container = DockerContainerFactory.createAndStartContainer(dockerImageType);
		containers.add(container);
		return container;
	}
	
	@AfterClass
	public static void tearDownDockerTestBase() {
		for (AbstractDockerContainer container : containers) {
			container.stop();
			container.delete();
		}
		
		containers.clear();
	}
}
