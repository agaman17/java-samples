package ro.gboss.ejbServer.client;

import ro.gboss.ejbServer.common.RemoteBeanContextFactory;
import ro.gboss.remoteEjb.server.SampleRemoteBean;

public class RemoteEjbClient {
	public static void main(String[] args) throws Exception {
		executeAnotherWay();
	}

	private static void executeAnotherWay() throws Exception {
		SampleRemoteBean srb = (SampleRemoteBean) RemoteBeanContextFactory
				.lookupBean("ejb:/jboss-as-ejb-remote-app//SampleRemoteBeanImpl!ro.gboss.remoteEjb.server.SampleRemoteBean");
		String s = srb.echoWhatYouSaid("The fuck ? Second edition");
		System.out.println(s);
	}
}
