package test.testCase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import container.ContainerStatus;

public class TestContainerStatusJSON {

	public ContainerStatus containerStatus;
	
	
	@Before
	public void setUp() throws Exception {
		this.containerStatus = new ContainerStatus("{\"containerId\":\"10008\",\"temperature\":\"30\"}");
	}

	@Test
	public void test() {
		Assert.assertTrue("10008".equalsIgnoreCase(this.containerStatus.getContainerId()));
		Assert.assertTrue("30".equalsIgnoreCase(this.containerStatus.getTemperature()));
	}

}
