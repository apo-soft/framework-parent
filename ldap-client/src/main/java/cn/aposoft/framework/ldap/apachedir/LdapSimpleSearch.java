/**
 * 
 */
package cn.aposoft.framework.ldap.apachedir;

import java.io.IOException;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.LdapConnection;

/**
 * @author LiuJian
 *
 */
public class LdapSimpleSearch {

    /**
     * @param args
     * @throws LdapException
     * @throws IOException
     */
    public static void main(String[] args) throws LdapException, IOException {
        // Simple search
        try (LdapConnection connection = ConnectionFactory.getConnection();) {
            try (EntryCursor cursor = connection.search("dc=aposoft,dc=cn", "(objectclass=organization)", SearchScope.ONELEVEL);) {
                System.err.println("any entry available?" + cursor.available());
                for (Entry entry : cursor) {
                    System.err.println(entry);
                }
            }
        }
    }

}
