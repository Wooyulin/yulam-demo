package cn.yulam.single;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * @author 5yl
 * date: 2022/4/12
 */
public class Singleton implements Serializable {
    private Singleton() {
    }

    private static class Inner {
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return Inner.instance;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        byte[] serialize = SerializationUtils.serialize(instance);
        Singleton newInstance = SerializationUtils.deserialize(serialize);
        System.out.println(instance == newInstance);
    }
}
