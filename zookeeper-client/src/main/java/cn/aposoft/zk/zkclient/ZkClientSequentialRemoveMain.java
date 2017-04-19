/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.zk.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author LiuJian
 * @date 2016年11月12日
 * 
 */
public class ZkClientSequentialRemoveMain {

    /**
     * 删除效率, 大约213次/秒
     * 
     * @param args
     */
    public static void main(String[] args) {
        // /nps1
        ZkClient zc = new ZkClient("192.168.80.128:2181/test", 3000);
        // String nps1 = zc.create("/nps1", "0",
        // CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("begin:" + System.currentTimeMillis());
        for (int i = 0; i <= 240004; i++) {
            String path = "/seq/nps10000" + String.valueOf((1000000 + i)).substring(1);
            if (zc.exists(path)) {
                zc.delete(path);
            }
        }
        System.out.println("end:" + System.currentTimeMillis());
        zc.close();
    }

}
