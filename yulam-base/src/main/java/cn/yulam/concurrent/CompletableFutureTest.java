package cn.yulam.concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void simple() {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1: 洗水壶");
            sleep(1000);
            System.out.println("T1: 烧开水");
            sleep(15000);
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶壶...");
            sleep(1000);

            System.out.println("T2: 洗茶杯...");
            sleep(2000);

            System.out.println("T2: 拿茶叶...");
            sleep(1000);
            return " 龙井 ";
        });

        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1: 拿到茶叶:" + tf);
                    System.out.println("T1: 泡茶...");
                    return " 上茶:" + tf;
                });
        System.out.println(f3.join());
    }

    public static void testThen() {
        /**
         * thenApply
         * 接受一个参数，一个返回值
         */
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> "nihao")
                .thenApply(s -> s + " zyf")
//                .thenApply(String::toUpperCase);
                .thenApply(String::toUpperCase);
        System.out.println("thenApply: " + result.join());

        /**
         * thenAccept
         * 无返回值
         */
        CompletableFuture<Void> accept = CompletableFuture.supplyAsync(() -> "no then accept")
                .thenAccept(System.out::println);
        System.out.println("thenAccept:" + accept.join());

        /**
         * thenRun
         * 无入参，无返回 then run
         *
         */
        CompletableFuture<Void> thenRun = CompletableFuture.supplyAsync(() -> "then run")
                .thenRun(() -> System.out.println("无入参，无返回 then run "));
        System.out.println("thenRun: " + thenRun.join());
        /**
         * thenCompose
         * 场景是一个 String 的caf 转换为一个Integer的caf
         */
        CompletableFuture<Integer> length = CompletableFuture.supplyAsync(() -> "nihao")
//                .thenApply(String::toUpperCase);
                .thenCompose(s ->
                        CompletableFuture.supplyAsync(s::length)
                );
        System.out.println("thenCompose: " + length.join());
    }

    public static void main(String[] args) {
        testThen();

    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
