package cn.yulam.downloadv2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 5yl
 * date: 2022/7/28
 */
public class ArrayBlockingQueueContainer implements Container{

    private ArrayBlockingQueue<Job> queue;

    @Override
    public boolean save(String recordVideoId) {
        return false;
    }

    @Override
    public Job obtain() {
        return null;
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public String getIdentifier() {
        return null;
    }
}
