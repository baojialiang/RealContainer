package test.testCase;

import junit.framework.Assert;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;

import container.ContainerRequest;

public class TestContainerRequestJSON {

	public ContainerRequest containerRequest;
	
	@Before
	public void setUp() throws Exception {
		this.containerRequest = new ContainerRequest();
	}

	@Test
	public void test() {
		String json = "{\"containerId\":\"10008\",\"operation\":\"open\"}";
		containerRequest.setContainerId("10008");
		containerRequest.setOperation("open");
		String containerRequestJSON = JSONObject.fromObject(containerRequest).toString();
		Assert.assertTrue(json.equalsIgnoreCase(containerRequestJSON));
	}

}