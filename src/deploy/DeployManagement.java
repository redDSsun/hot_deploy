package deploy;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class DeployManagement {
    private Socket client;
    private FileInputStream fis;
    private DataOutputStream dos;

    public Socket connectServer (String ip, int port) throws IOException {
        client = new Socket(ip, port);
        return client;
    }

    public void sendFile(String data) throws IOException {
//        File file=new File(url);
//        Map file = map;
        try {
            fis = new FileInputStream(data);
            //BufferedInputStream bi=new BufferedInputStream(new InputStreamReader(new FileInputStream(file),"GBK"));
            dos = new DataOutputStream(client.getOutputStream());//client.getOutputStream()返回此套接字的输出流
//            //文件名、大小等属性
//            dos.writeUTF(file.getName());
//            dos.flush();
//            dos.writeLong(file.length());
//            dos.flush();
            // 开始传输文件
            System.out.println("======== 开始传输文件 ========");
            byte[] bytes = new byte[1024];
            int length = 0;

            while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                dos.write(bytes, 0, length);
                dos.flush();
            }
            System.out.println("======== 文件传输成功 ========");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("客户端文件传输异常");
        }finally{
            fis.close();
            dos.close();
        }
    }

}
