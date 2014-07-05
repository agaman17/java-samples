package ro.gboss.ejbServer.common;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteBeanContextFactory {
	private static final String _SERVER_URL = "192.168.56.101";
	private static final String _SERVER_PORT = "4447";
	private static final String _USERNAME = "appuser";
	private static final String _PASSWORD = "123!@#aA";

	@SuppressWarnings({ "unchecked" })
	public static <T> T lookupBean(String beanName) throws NamingException {
		final Context context = RemoteBeanContextFactory.getContext();
		return (T) context.lookup(beanName);
	}

	public static Context getContext() throws NamingException {
		final Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL,
				String.format("remote://%s:%s", _SERVER_URL, _SERVER_PORT));
		jndiProperties.put(Context.SECURITY_PRINCIPAL, _USERNAME);
		jndiProperties.put(Context.SECURITY_CREDENTIALS, _PASSWORD);
		jndiProperties.put("jboss.naming.client.ejb.context", "true");

		final Context context = new InitialContext(jndiProperties);
		return context;
	}
}
