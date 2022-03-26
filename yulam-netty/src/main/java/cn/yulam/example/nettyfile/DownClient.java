package cn.yulam.example.nettyfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 5yl
 * date: 2022/3/25
 */
public class DownClient extends Socket{
    private  final String SERVER_IP="127.0.0.1";
    private final int SERVER_PORT=8023;
    private Socket client;
    private DataInputStream dis;
    private FileOutputStream fos;

    //创建客户端，并指定接收的服务端IP和端口号
    public DownClient() throws IOException{
        this.client=new Socket(SERVER_IP,SERVER_PORT);
        System.out.println("成功连接服务端..."+SERVER_IP);
    }

    //向服务端传输文件
//    public void sendFile(String url) throws IOException {
//        File file=new File(url);
//        try {
//            fis = new FileInputStream(file);
//            //BufferedInputStream bi=new BufferedInputStream(new InputStreamReader(new FileInputStream(file),"GBK"));
//            dos = new DataOutputStream(client.getOutputStream());//client.getOutputStream()返回此套接字的输出流
//            //文件名、大小等属性
//            dos.writeUTF(file.getName());
//            dos.flush();
//            dos.writeLong(file.length());
//            dos.flush();
//            // 开始传输文件
//            System.out.println("======== 开始传输文件 ========");
//            byte[] bytes = new byte[1024];
//            int length = 0;
//
//            while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
//                dos.write(bytes, 0, length);
//                dos.flush();
//            }
//            System.out.println("======== 文件传输成功 ========");
//        }catch(IOException e){
//            e.printStackTrace();
//            System.out.println("客户端文件传输异常");
//        }finally{
//            fis.close();
//            dos.close();
//        }
//    }

    public void downloadFile() {
        System.out.println(" Ip:"+client.getInetAddress()+"已连接");
        try {
            OutputStream outputStream = client.getOutputStream();
            Byte aByte = new Byte("111.txt");
            outputStream.write(aByte);
            outputStream.flush();
            dis = new DataInputStream(client.getInputStream());
            // 文件名和长度
            String fileName = dis.readUTF();
            long fileLength = dis.readLong();
            File directory = new File("H:/TEST");
            if(!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);

            fos = new FileOutputStream(file);
            System.out.println("file。。。。。。。。。。。。。。"+file);
            System.out.println("fileName。。。。。。。。。。。。。。"+fileName);

            System.out.println("======== 开始接收文件 ========");
            byte[] bytes = new byte[1024];
            int length = 0;
            while((length = dis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, length);
                fos.flush();
            }

            System.out.println("======== 文件接收成功 ========");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos != null) {
                    fos.close();
                }
                if(dis != null) {
                    dis.close();
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        try {
            DownClient client = new DownClient(); // 启动客户端连接
            client.downloadFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

