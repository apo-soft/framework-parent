/**
 * 
 */
package cn.aposoft.framework.ldap.apachedir;

import java.io.IOException;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;

/**
 * @author LiuJian
 *
 */
public class PersonSearchExample {

    /**
     * @param args
     * @throws IOException
     * @throws LdapException
     */
    public static void main(String[] args) throws IOException, LdapException {
        // Simple search
        try (LdapConnection connection = ConnectionFactory.getConnection();) {
            /*
             * try (EntryCursor cursor =
             * connection.search("ou=Work,o=Wechat,dc=aposoft,dc=cn",
             * "(objectclass=*)", SearchScope.OBJECT);) {
             * System.err.println("any entry available?" + cursor.available());
             * for (Entry entry : cursor) { System.err.println(entry); } }
             */
            System.err.println("use one dn.");
            Dn dn = new Dn("cn=liujian+sn=liujian,ou=Work,o=Wechat,dc=aposoft,dc=cn");
            try (EntryCursor cursor = connection.search(dn, "(objectClass=person)", SearchScope.SUBTREE);) {
                System.err.println("any entry available?" + cursor.available());
                for (Entry entry : cursor) {
                    System.err.println(entry);
                }
            }
        }
    }

}
