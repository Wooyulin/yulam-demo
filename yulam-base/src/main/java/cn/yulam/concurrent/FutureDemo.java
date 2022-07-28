package cn.yulam.concurrent;

import java.util.concurrent.*;

public class FutureDemo {

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    //二分查找
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //获取UTC时间戳
    public static long getUTCTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }





    int calculatehoursbetweenDays(int day1, int day2) {
        int hours = 0;
        if (day1 > day2) {
            hours = (day1 - day2) * 24;
        } else {
            hours = (day2 - day1) * 24;
        }
        return hours;
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<Object> future = executorService.submit((Callable<Object>) () -> {
//            Long start = System.currentTimeMillis();
//            while (true) {
//                Long current = System.currentTimeMillis();
//                if ((current - start) > 1000) {
//                    return 1;
//                }
//            }
//        });
//        Integer o = (Integer)future.get();
//        System.out.println(o);

//        Future<?> submit = executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                Long start = System.currentTimeMillis();
//                while (true) {
//                    Long current = System.currentTimeMillis();
//                    if ((current - start) > 50) {
//                        break;
//                    }
//                    System.out.println(System.currentTimeMillis());
//
//                }
//            }
//        });
//
//        Object o1 = submit.get();
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();

        if(!futureTask.isDone()) {
            System.out.println("Task is not done");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            // 5. 调用get()方法获取任务结果,如果任务没有执行完成则阻塞等待
            result = futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("result is " + result);
    }
}

/**
 * 实现 callable  有返回值
 * 需要包装再一个future 执行
 */
class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(10000);
        System.out.println("thread name is " + Thread.currentThread().getName());
        return 2;
    }
}
