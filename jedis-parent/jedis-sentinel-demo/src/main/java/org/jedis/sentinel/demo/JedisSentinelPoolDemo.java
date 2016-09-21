/**
 *   Copyright  :  www.aposoft.cn
 */
package org.jedis.sentinel.demo;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author LiuJian
 * @date 2016年9月4日
 * 
 */
public class JedisSentinelPoolDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String masterHost = "redis-master";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("redis-master:5000");
        sentinels.add("redis-master:5001");
        sentinels.add("redis-master:5002");
        try (JedisSentinelPool pool = new JedisSentinelPool(masterHost, sentinels, "GomeFinance");) {

            Jedis jedis = pool.getResource();

            jedis.set("java-client-test-key", "test-v");

            System.out.println(jedis.get("java-client-test-key"));
            jedis.close();

            pool.destroy();
        }
    }
}
