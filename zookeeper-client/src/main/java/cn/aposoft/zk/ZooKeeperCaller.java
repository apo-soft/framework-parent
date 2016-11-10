/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.zk;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import com.alibaba.fastjson.JSON;

/**
 * @author LiuJian
 * @date 2016年11月9日
 * 
 */
public class ZooKeeperCaller {

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     * @throws KeeperException
     */
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Watcher watcher = new BasicZooKeeperWatcher();

        ZooKeeper zk = new ZooKeeper("192.168.80.128:2181/test", 3000, watcher);
        // getData(zk);
        // exists(zk);
        // create(zk);
        createPersist(zk);
        try {
            Thread.sleep(1000 * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zk.close();
    }

    public static void createPersist(ZooKeeper zk) throws KeeperException, InterruptedException {
        // 读取信息
        Stat stat = zk.exists("/persistNode1", false);
        System.out.println("Stat for /persistNode1:" + JSON.toJSONString(stat));
        if (stat != null) {
            getData(zk, stat, "/persistNode1");
        } else {
            List<ACL> acls = Collections.emptyList();
            String persistNode = zk.create("/persistNode1", "this is data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(persistNode);
            try {
                int i = System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getData(ZooKeeper zk) throws KeeperException, InterruptedException {
        getData(zk, null);
        exists(zk);
    }

    public static void getData(ZooKeeper zk, Stat stat) throws KeeperException, InterruptedException {
        getData(zk, stat, "/");
    }

    public static void getData(ZooKeeper zk, Stat stat, String node) throws KeeperException, InterruptedException {
        // 读取信息
        byte[] data = zk.getData(node, false, stat);

        if (data != null) {
            System.out.println(new String(data));
        }
    }

    public static void exists(ZooKeeper zk) throws KeeperException, InterruptedException {
        // 读取信息
        Stat stat = zk.exists("/", false);
        System.out.println(JSON.toJSONString(stat));
        if (stat != null) {
            getData(zk, stat);
        }
    }

    public static void create(ZooKeeper zk) throws KeeperException, InterruptedException {
        // 读取信息
        Stat stat = zk.exists("/persistNode", false);
        System.out.println(JSON.toJSONString(stat));
        if (stat != null) {
            getData(zk, stat, "/persistNode");
        } else {
            List<ACL> acls = Collections.emptyList();
            String persistNode = zk.create("/persistNode", "this is data".getBytes(), acls, CreateMode.PERSISTENT);
            System.out.println(persistNode);
            try {
                int i = System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
