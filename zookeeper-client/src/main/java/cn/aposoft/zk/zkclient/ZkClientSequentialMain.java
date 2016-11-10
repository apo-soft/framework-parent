/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.zk.zkclient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @author LiuJian
 * @date 2016年11月9日
 * 
 */
public class ZkClientSequentialMain {
    static Object o = new Object();
    static ConcurrentMap<String, Object> keys = new ConcurrentHashMap<String, Object>();

    /**
     * @param args
     */
    public static void main(String[] args) {
        int count = 30;
        final CountDownLatch prepare = new CountDownLatch(count);
        final CountDownLatch run = new CountDownLatch(1);
        final CountDownLatch finish = new CountDownLatch(count);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            try {
                Task task1 = new Task(prepare, run, finish);
                service.execute(task1);
            } catch (Exception e) {
                System.out.println("current:" + i);
                e.printStackTrace();
            }

        }

        try {
            prepare.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        run.countDown();

        try {
            finish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        List<String> list = Arrays.asList(keys.keySet().toArray(new String[0]));
        Collections.sort(list);
        for (String key : list) {
            System.out.println(key);
        }
    }

    public static class Task implements Runnable {
        ZkClient zc = new ZkClient("192.168.80.128:2181/test", 3000);
        private CountDownLatch prepare, run, finish;

        public Task(CountDownLatch prepare, CountDownLatch run, CountDownLatch finish) {
            this.prepare = prepare;
            this.run = run;
            this.finish = finish;
        }

        @Override
        public void run() {
            prepare.countDown();

            try {
                run.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long begin = System.currentTimeMillis();
            System.out.println(begin);
            for (int i = 0; i < 1000; i++) {
                String nps1 = zc.create("/nps1", "0", CreateMode.PERSISTENT_SEQUENTIAL);
                Object obj = keys.putIfAbsent(nps1, o);
                if (obj != null) {
                    System.out.println("conflictKey:" + nps1);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(end + "-" + (end - begin));
            finish.countDown();
            zc.close();
        }
    }

}
