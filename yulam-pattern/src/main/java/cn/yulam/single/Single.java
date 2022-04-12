package cn.yulam.single;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author 5yl
 * date: 2022/4/12
 */

/**
 * 饿汉式
 * 优点：简单快捷
 * 缺点：不管用不用都初始化
 */
class HungerSingle {
    private static HungerSingle instance = new HungerSingle();
    private HungerSingle() {

    }

    public static HungerSingle getInstance() {
        return instance;
    }
}

/**
 * 解决饿汉式预初始化的缺点
 * 缺点：锁粒度大，性能低
 */
class LazySingle {
    private static LazySingle instance;

    private LazySingle() {

    }

    public static synchronized LazySingle getInstance() {
        if (instance == null) {
            instance = new LazySingle();
        }
        return instance;
    }
}

/**
 * DCL 单例
 * 对比懒汉式，提升了锁性能
 */
class DCLSingle {
    /**
     禁止重排序，保证变量线程间可见
     * 重排序情况：o = new o() 会被拆分称三个指令，
     * 1. 分配空间
     * 2。实例化
     * 3.将变量指向内存空间
     * 上述三个指令有可能是同时执行的，这个情况会出现instance 是一个只指向了内存地址，但是实际上并没有实例化的空对象
     */
    private static volatile DCLSingle instance;
    private DCLSingle() {
    }
    public static DCLSingle getInstance() {
        if (instance == null) {
            synchronized (DCLSingle.class) {
                if (instance == null) {
                    //第一次判空，是存在并发的
                    instance = new DCLSingle();
                }
            }
        }
        return instance;
    }
}

/**
 * 静态内部类形式
 * 1.静态内部类，在调用到的时候才会去创建实例
 * 2。
 *
 * 类初始化的几个时机
 * 1、new 或者调用静态方法
 * 2、初始化子类的时候，会去初始化父类
 * 3、反射调用类的时候
 * 4、主类启动
 *
 * 静态内部类的线程安全交给JVM进行保证，看似已经是最好的方式了，
 * 至今未解决的缺点 ： 但是还有一个就是反射修改和序列化方式的方式新建
 */
class StaticInnerSingle {
    private StaticInnerSingle() {
    }
    private static class Inner {
        private static StaticInnerSingle instance = new StaticInnerSingle();
    }
    public static StaticInnerSingle getInstance() {
        return Inner.instance;
    }


}

/**
 * 最假单例实现方式，解决了线程问题、序列化、反射问题
 * 优点
 * 1、代码简单
 * 2、解决了反射创建
 *      因为反射里面的newinstance()里面写死了如果是枚举类则反射创建不允许 java.lang.reflect.Constructor#newInstance(java.lang.Object...)
 * 3、序列化问题解决
 *      JVM特殊处理，在调用readObject的时候 回去堆中找枚举对象，不会新建
 *
 */
enum EnumSingle {
    /**
     * 单例
     */
    INSTANCE;

    public EnumSingle getInstance(){
        return INSTANCE;
    }
}


/**
 * 单例四大原则
 * 1. 构造方法私有化
 * 2。静态化或者枚举方式返回对象
 * 3. 反序列化也不能创建对象
 * 4、多线程
 *
 *
 */
public class Single {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        StaticInnerSingle instance = StaticInnerSingle.getInstance();
//        Constructor<StaticInnerSingle> declaredConstructor = StaticInnerSingle.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);
//        StaticInnerSingle staticInnerSingle = declaredConstructor.newInstance();
//        System.out.println(staticInnerSingle == instance);
        SerEnumSingleton instance = SerEnumSingleton.INSTANCE;
//        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);
//        EnumSingle enumSingle = declaredConstructor.newInstance();
//        System.out.println(instance == enumSingle);


        byte[] serialize = SerializationUtils.serialize(instance);
        SerEnumSingleton deserialize = SerializationUtils.deserialize(serialize);
        System.out.println(deserialize == instance);
    }
}
