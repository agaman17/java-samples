package ro.gboss.ejbServer.integrationTests;

import javax.naming.NamingException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ro.gboss.ejbServer.common.RemoteBeanContextFactory;
import ro.gboss.remoteEjb.server.SampleRemoteBean;

public class SampleRemoteBeanIntegrationTest {

	private static SampleRemoteBean _srb = null;

	@BeforeClass
	public static void initializeClass() throws NamingException {
		System.out
				.println("Initializing integration test on SampleRemoteBean class...");
		_srb = (SampleRemoteBean) RemoteBeanContextFactory
				.lookupBean("ejb:/jboss-as-ejb-remote-app//SampleRemoteBeanImpl!ro.gboss.remoteEjb.server.SampleRemoteBean");
		System.out.println("Bean class name : " + _srb.getClass().toString());
	}

	@AfterClass
	public static void cleanup() {
		System.out
				.println("Cleaning up integration test on SampleRemoteBeanClass");
		if (_srb != null)
			_srb = null;
	}

	@Test
	public void testRemoteBeanLookup() {
		Assert.assertNotNull(_srb);
	}

	@Test
	public void RemoteBeanSmokeTest() {
		String test = "this is a test";
		String comparisonObject = "You said : " + test;

		String beanResultObject = _srb.echoWhatYouSaid(test);
		Assert.assertEquals(comparisonObject, beanResultObject);
	}
}
