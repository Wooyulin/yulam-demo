package cn.yulam.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 5yl
 * date: 2022/3/23
 */
public class StartMethod {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("############## runnable ############");
        System.out.println(Thread.currentThread().getName());
        new Thread(new MyRunnable()).start();
       // System.out.println("###############callable###########");


        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
        System.out.println("ft result = " + futureTask.get());


        new MyThread().start();

    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("thread name is " + Thread.currentThread().getName());
    }
}

/**
 * 实现 callable  有返回值
 * 需要包装再一个future 执行
 */
class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("thread name is " + Thread.currentThread().getName());
        return 2;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("thread name is " + Thread.currentThread().getName());
    }
}


