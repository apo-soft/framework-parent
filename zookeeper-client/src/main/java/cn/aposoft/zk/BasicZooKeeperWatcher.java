/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import com.alibaba.fastjson.JSON;

/**
 * ZooKeeper 客户端
 * 
 * @author LiuJian
 * @date 2016年11月9日
 * 
 */
public class BasicZooKeeperWatcher implements Watcher {

    /**
     * 简单打印 时间内容
     * 
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
     */
    @Override
    public void process(WatchedEvent event) {
        System.out.println("Client Watcher:" + JSON.toJSONString(event));
    }
}
