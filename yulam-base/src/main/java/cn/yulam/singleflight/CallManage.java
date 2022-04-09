package cn.yulam.singleflight;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * https://pkg.go.dev/golang.org/x/sync/singleflight
 * @author 5yl
 * date: 2022/4/9
 */
public class CallManage {
    private final Lock lock = new ReentrantLock();
    private Map<String, Call> callMap;

    public String run(String key, Supplier<String> func) {
        this.lock.lock();
        if (this.callMap == null) {
            this.callMap = new HashMap<>();
        }
        Call call = this.callMap.get(key);
        if (call != null) {
            this.lock.unlock();
            call.await();
            return call.getVal();
        }
        call = new Call();
        call.lock();
        this.callMap.put(key, call);
        this.lock.unlock();

        call.setVal(func.get());
        call.done();

        this.lock.lock();
        this.callMap.remove(key);
        this.lock.unlock();

        return call.getVal();
    }
}
