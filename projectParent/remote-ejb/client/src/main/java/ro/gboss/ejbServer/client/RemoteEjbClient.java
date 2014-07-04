package ro.gboss.ejbServer.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ro.gboss.remoteEjb.server.SampleRemoteBean;
import ro.gboss.remoteEjb.server.SampleRemoteBeanImpl;

public class RemoteEjbClient 
{
	public static void main(String[] args) throws Exception
	{
		final SampleRemoteBean srb = lookup();
		String s = srb.echoWhatYouSaid("The fuck ?");
		System.out.println(s);
		
		executeAnotherWay();
	}
	
	private static void executeAnotherWay() throws Exception
	{
		SampleRemoteBean srb = (SampleRemoteBean) RemoteBeanContextFactory.lookupBean("ejb:/jboss-as-ejb-remote-app//SampleRemoteBeanImpl!ro.gboss.remoteEjb.server.SampleRemoteBean");
		String s = srb.echoWhatYouSaid("The fuck ? Second edition");
		System.out.println(s);
	}
	
	private static SampleRemoteBean lookup() throws NamingException
	{
		final Context context = RemoteBeanContextFactory.getContext();
		final String lookupString = "ejb:/jboss-as-ejb-remote-app//SampleRemoteBeanImpl!ro.gboss.remoteEjb.server.SampleRemoteBean";
		
		System.out.println("Doing lookup via jndi...");
		System.out.println(lookupString);
		return (SampleRemoteBean) context.lookup("ejb:/jboss-as-ejb-remote-app//SampleRemoteBeanImpl!ro.gboss.remoteEjb.server.SampleRemoteBean");
	}

	static class RemoteBeanContextFactory
	{
		public static <T> T lookupBean(String beanName) throws NamingException
		{
			final Context context = RemoteBeanContextFactory.getContext();
			return (T) context.lookup(beanName);
		}
		
		public static Context getContext() throws NamingException
		{
			final Hashtable<Object,Object> jndiProperties = new Hashtable<Object, Object>();
			
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put(Context.PROVIDER_URL, "remote://10.88.11.230:4447");
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "appuser");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "123!@#aA");
			jndiProperties.put("jboss.naming.client.ejb.context", "true");
			
			final Context context = new InitialContext(jndiProperties);
			return context;
		}
	}
}
