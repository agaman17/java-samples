package ro.gboss.remoteEjb.server;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(SampleRemoteBean.class)
public class SampleRemoteBeanImpl implements SampleRemoteBean {

	@Override
	public String echoWhatYouSaid(String s) {
		System.out.println("Echo bean method called...");
		System.out.println("Returning the following string : ");
		System.out.println("You said " + s);
		return "You said : " + s;
	}

}
