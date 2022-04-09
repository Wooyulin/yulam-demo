package cn.yulam.singleflight;

import java.util.concurrent.CountDownLatch;

/**
 * @author 5yl
 * date: 2022/4/9
 */
public class Call {
    private String val;
    private CountDownLatch cld;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public void await() {
        try {
            this.cld.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lock() {
        this.cld = new CountDownLatch(1);
    }

    public void done() {
        this.cld.countDown();
    }
}
