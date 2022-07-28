package cn.yulam.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    static ThreadLocal<String> TTL = new TransmittableThreadLocal<>();
    static ThreadLocal<String> TTL2 = new InheritableThreadLocal<>();
    //    static ThreadLocal<String> TTL = new ThreadLocal<>();
    static TransmittableThreadLocal<String> context = new TransmittableThreadLocal<String>();


    public static void main(String[] args) throws Exception {
//        new Thread(() -> {
//            // 在父线程中设置变量
//            TTL.set("throwable");
//            new Thread(() -> {
//                methodFrame1();
//            }).start();
//            try {
//                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 额外的处理，生成修饰了的对象executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);

//        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<String>();
//        InheritableThreadLocal<String> context = new InheritableThreadLocal<String>();
//        ThreadLocal<String> context = new ThreadLocal<String>();
        context.set("value-set-in-parent");
        Runnable task = () -> {
            System.out.println(context.get());
            new Thread(() -> {
                methodFrame1();
            }).start();
        };
        executorService.submit(task);

    }

//    public static void

    private static void methodFrame1() {
        methodFrame2();
    }

    private static void methodFrame2() {
        System.out.println(context.get());
    }
}
