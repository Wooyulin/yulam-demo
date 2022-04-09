package cn.yulam.singleflight;

import java.util.concurrent.CountDownLatch;

/**
 * @author 5yl
 * date: 2022/4/9
 */
public class Test {

    public static void main(String[] args) {
        CallManage callManage = new CallManage();
        int count = 10;
        CountDownLatch cld = new CountDownLatch(count);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    cld.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String value = callManage.run("key", () -> {
                    System.out.println("func");
                    return "bar";
                });
                System.out.println(value);
            }).start();
            cld.countDown();
        }
        System.out.println();
    }

}
