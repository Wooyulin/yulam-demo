package cn.yulam.retry;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
public class RetryService {

    @Retryable
    public void call() throws Exception {
        queryOrder();
    }

    @Recover
    public void callFail() {
        System.out.println("预警");
    }

    private void queryOrder() throws Exception {
        throw new TimeoutException("超时");
    }
}
