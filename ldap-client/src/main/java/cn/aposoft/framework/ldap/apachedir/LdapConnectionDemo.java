package cn.aposoft.framework.ldap.apachedir;

import java.io.IOException;

import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

public class LdapConnectionDemo {
    public static void main(String[] args) throws IOException {
        try (LdapConnection connection = new LdapNetworkConnection("192.168.199.128", 389);) {
            connection.setTimeOut(0);
            System.out.println("connected.");
        }
    }
}
