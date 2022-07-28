package cn.yulam.downloadv2;

/**
 * @author 5yl
 * date: 2022/7/28
 */
public interface Container {

    /**
     * 提交任务
     * @param recordVideoId
     * @return
     */
    boolean save(String recordVideoId);

    /**
     * 获取任务
     * @return
     */
    Job obtain();
    /**
     * 获取大小
     * @return
     */
    int getContainerSize();

    /**
     * 获取唯一标识
     * @return
     */
    String getIdentifier();
}
