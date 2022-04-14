package cn.yulam.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 5yl
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * date: 2022/4/14
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
