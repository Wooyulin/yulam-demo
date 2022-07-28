package cn.yulam.downloadv2;

/**
 * @author 5yl
 * date: 2022/7/28
 */
public class Job {

    String recordVideoId;

    int state;

    public String getRecordVideoId() {
        return recordVideoId;
    }

    public void setRecordVideoId(String recordVideoId) {
        this.recordVideoId = recordVideoId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
