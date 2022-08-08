package cn.yulam.downloadv2;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 5yl
 * date: 2022/7/28
 */
public class ContainerPool<T extends Container> {

    private static final AtomicInteger ROBIN_INDEX = new AtomicInteger(0);
    private static final ScheduledExecutorService TIMER_EXECUTOR = Executors.newScheduledThreadPool(2);
    public Map<String, T> containers = new ConcurrentHashMap<>();

    public List<String> identifiers = new CopyOnWriteArrayList<>();


    /**
     * 注册
     *
     * @param container
     */
    public void register(T container) {
        if (containers.containsKey(container.getIdentifier())) {
            containers.put(container.getIdentifier(), container);
            identifiers.add(container.getIdentifier());
        }
    }

    /**
     * 获取任务
     *
     * @return
     */
    public Job obtain() {

        return null;
    }

    /**
     * 后续修改为多种获取策略，目前先实现轮询
     *
     * @return
     */
    private Job get() {
        Job result = null;
        if (ROBIN_INDEX.get() >= this.identifiers.size()) {
            ROBIN_INDEX.set(0);
        }
        for (int i = ROBIN_INDEX.get(); i < this.identifiers.size() && Objects.isNull(result); ROBIN_INDEX.addAndGet(1)) {

            String identifier = this.identifiers.get(i);
            result = this.containers.get(identifier).obtain();
        }
        return result;
    }

}
