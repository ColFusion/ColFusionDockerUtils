package edu.pitt.sis.ext.colfusion.docker.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.pitt.sis.ext.colfusion.docker.utils.containers.MySQLDockerContainer;

public class DockerTest extends DockerTestBase {

	@Test
	public void testDocker() throws Exception {
		MySQLDockerContainer mysqlContainer = (MySQLDockerContainer) createAndStartContainer(DockerImageType.MYSQL_IMAGE);
		
		assertNotNull(mysqlContainer);
		
		assertNotNull(mysqlContainer.getMySQLConnectionInfo());
	}
}
