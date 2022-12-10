package cn.yulam.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.InputStream;
import java.util.Properties;

public class RabbitMqUtil {

    /**
     * 主机名 即 Linux IP地址
     */
    private static String host = "";
    /**
     *  端口号 客户端访问默认都是 5672
     */
    private static int port = 0;
    /**
     * 虚拟主机 可以设置为默认的 / 或者自己创建出指定的虚拟主机
     */
    private static String virtualHost = "";
    /**
     * 用户名
     */
    private static String username = "";
    /**
     * 密码
     */
    private static String password = "";

    // 使用静态代码块为Properties对象赋值
    static {
        try {
            //实例化对象
            Properties properties = new Properties();
            //获取properties文件的流对象
            InputStream in = RabbitMqUtil.class.getClassLoader().getResourceAsStream("rabbitmq.properties");
            properties.load(in);
            // 分别获取 value
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));
            virtualHost = properties.getProperty("virtualHost");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     *
     * @return 连接
     */
    public static Connection getConnection() {
        try {
            // 创建连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();
            // 设置连接 rabbitmq 主机
            connectionFactory.setHost(host);
            // 设置端口号
            connectionFactory.setPort(port);
            // 设置连接的虚拟主机（数据库的感觉）
//            connectionFactory.setVirtualHost(virtualHost);
//            // 设置访问虚拟主机的用户名和密码
//            connectionFactory.setUsername(username);
//            connectionFactory.setPassword(password);
            // 返回一个新连接
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭通道和释放连接
     *
     * @param channel    channel
     * @param connection connection
     */
    public static void close(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
