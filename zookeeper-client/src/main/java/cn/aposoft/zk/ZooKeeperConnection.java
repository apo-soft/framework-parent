/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.zk;

import java.io.IOException;
import java.util.Arrays;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.alibaba.fastjson.JSON;

/**
 * @author LiuJian
 * @date 2016年11月10日
 * 
 */
public class ZooKeeperConnection {

    /**
     * 
     * @param args
     * @throws IOException
     * @throws InterruptedException
     * @throws KeeperException
     */
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Watcher watcher = new BasicZooKeeperWatcher();
        // System.out.println("begin:");
        ZooKeeper zooKeeper = new ZooKeeper("192.168.80.128:2181/test", 3000, watcher);
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPassword = zooKeeper.getSessionPasswd();
        System.out.println(sessionId);
        System.out.println(Arrays.toString(sessionPassword));
        Stat st = new Stat();
        ZooKeeper zooKeeper1 = new ZooKeeper("192.168.80.128:2181/test", 3000, watcher, sessionId, sessionPassword);
        byte[] data1 = zooKeeper1.getData("/", false, st);
        System.out.println(Arrays.toString(data1));
        System.out.println(JSON.toJSONString(st));
        byte[] data = zooKeeper.getData("/", false, st);
        System.out.println(Arrays.toString(data));
        System.out.println(JSON.toJSONString(st));
        zooKeeper.close();
        byte[] data2 = zooKeeper1.getData("/", false, null);
        System.out.println(zooKeeper1.getSessionTimeout());
        System.out.println(Arrays.toString(data2));
        zooKeeper1.close();
    }

}
