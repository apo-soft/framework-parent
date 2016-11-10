/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.zk.zkclient;

import java.io.IOException;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author LiuJian
 * @date 2016年11月10日
 * 
 */
public class ZkClientEphemeralNodesMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ZkClient zc = new ZkClient("192.168.80.128:2181/test", 3000);
        String nodePath = "/EphemeralNodes";
        zc.createEphemeral(nodePath, "nothing");

        String data = zc.readData(nodePath);
        System.out.println(data);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        zc.close();
    }

}
