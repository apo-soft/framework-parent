/**
 * 
 */
package cn.aposoft.framework.ldap.apachedir;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.directory.ldap.client.api.DefaultLdapConnectionFactory;
import org.apache.directory.ldap.client.api.DefaultPoolableLdapConnectionFactory;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapConnectionConfig;
import org.apache.directory.ldap.client.api.LdapConnectionPool;

/**
 * @author LiuJian
 *
 */
public class LdapConnectionPoolDemo {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        LdapConnectionConfig config = new LdapConnectionConfig();
        config.setLdapHost("192.168.199.128");
        config.setLdapPort(389);
        config.setName("cn=Manager,dc=aposoft,dc=cn");
        config.setCredentials("liujian");

        DefaultLdapConnectionFactory factory = new DefaultLdapConnectionFactory(config);
        factory.setTimeOut(0);

        // optional, values below are defaults
        GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();
        poolConfig.lifo = true;
        poolConfig.maxActive = 8;
        poolConfig.maxIdle = 8;
        poolConfig.maxWait = -1L;
        poolConfig.minEvictableIdleTimeMillis = 1000L * 60L * 30L;
        poolConfig.minIdle = 0;
        poolConfig.numTestsPerEvictionRun = 3;
        poolConfig.softMinEvictableIdleTimeMillis = -1L;
        poolConfig.testOnBorrow = false;
        poolConfig.testOnReturn = false;
        poolConfig.testWhileIdle = false;
        poolConfig.timeBetweenEvictionRunsMillis = -1L;
        poolConfig.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
        LdapConnectionPool pool = null;
        try {
            pool = new LdapConnectionPool(new DefaultPoolableLdapConnectionFactory(factory), poolConfig);
            System.err.println("connection pool is created.");
            try (LdapConnection conn = pool.getConnection();) {
                System.err.println("connection is retrieved.");
                if (conn.isAuthenticated()) {
                    System.err.println("connection is Authenticated.");
                    conn.unBind();
                    System.err.println("connection is connected?" + conn.isConnected());
                }
                conn.bind("cn=Manager,dc=aposoft,dc=cn", "liujian");
                System.err.println("connection is connected?" + conn.isConnected());
                conn.unBind();
            }
        } finally {
            if (pool != null)
                pool.close();
        }
        System.err.println("connection pool is closed.");

    }

}
