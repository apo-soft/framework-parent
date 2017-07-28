/**
 * 
 */
package cn.aposoft.framework.ldap.apachedir;

import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

/**
 * Simple Ldap Connection Factory， return new connection each time
 * 
 * @author LiuJian
 * @since 1.0.0.6
 */
public class ConnectionFactory {

    public static LdapConnection getConnection() {
        LdapConnection connection = new LdapNetworkConnection("192.168.199.128", 389);
        return connection;
    }

}
