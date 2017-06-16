/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.cache.redis.lua;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisNoScriptException;

/**
 * @author LiuJian
 * @date 2016年11月14日
 * 
 */
public class RedisLuaMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String masterHost = "redis-master";
        JedisPoolConfig config = new JedisPoolConfig();

        JedisPool pool = new JedisPool(config, masterHost, 6379, 1000, "mymaster");
        String keyName = "name";
        Jedis jedis = pool.getResource();
        // jedis.auth("GomeFinance");
        System.out.println(jedis.set(keyName, "100", "XX", "EX", 100));

        System.out.println(jedis.ttl(keyName));
        System.out.println(jedis.decrBy(keyName, 5));

        System.out.println(jedis.getSet(keyName, "15"));
        System.out.println(jedis.get(keyName));
        final String lockName = "LOCK_NAME";
        String secret = String.valueOf(System.currentTimeMillis());
        String setOk = jedis.set(lockName, secret, "NX", "EX", 100);
        System.out.println(setOk);

        // cas 删除操作
        String sha1 = jedis.scriptLoad(
                "if redis.call(\"get\",KEYS[1]) == ARGV[1] then " + "     return redis.call(\"del\",KEYS[1]) " + "else " + "     return 0 " + "end");
        Object v = jedis.evalsha(sha1, 1, lockName, secret);
        System.out.println(v);
        try {
            Object o = jedis.evalsha("7df68595b658a742459f06f73deb90db3126cf2", 0);
            System.out.println(o);
        } catch (JedisNoScriptException e) {
            e.printStackTrace();
        }
       
        jedis.close();

        pool.close(); 
    }

    // script load ' return "HELLO" ' sha:
    // "7df68595b658a742459f06f73deb90db3126cf2a"
}
