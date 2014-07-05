package ro.gboss.ejbServer.unitTests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ro.gboss.remoteEjb.server.SampleRemoteBean;
import ro.gboss.remoteEjb.server.SampleRemoteBeanImpl;

public class SampleRemoteBeanUnitTest {
	private static SampleRemoteBean _srb = null;

	@BeforeClass
	public static void initialize() {
		System.out.println("Test class initialized...");
		_srb = new SampleRemoteBeanImpl();
	}

	@AfterClass
	public static void cleanup() {
		System.out.println("Test class closing...");
	}

	@Test
	public void echoMethodTest() {
		String testString = "teststring";
		String comparisonString = "You said : " + testString;

		String ejbResultString = _srb.echoWhatYouSaid(testString);

		Assert.assertEquals(comparisonString, ejbResultString);
	}
}
