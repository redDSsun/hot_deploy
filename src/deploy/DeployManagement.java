package deploy;

import com.google.gson.Gson;
import deploy.pojo.DeployFile;
import deploy.pojo.DeployInfo;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class DeployManagement {
    private Socket client = null;
    private FileInputStream fis;
    private ObjectOutputStream oos;

    public void connectServer (String ip, int port) throws IOException {
        client = new Socket(ip, port);
    }

    public Socket getConnect(){
        return client;
    }

    public void sendFile(ArrayList<DeployFile> deployFiles) throws IOException {
        ArrayList<DeployInfo> deployInfos = new ArrayList<>();
        for (DeployFile file: deployFiles ){
            deployInfos.add(new DeployInfo(file.getOperation(),file.getRelativepath()));
        }

        try {

//            fis = new BuffInputStream(deployInfos.toString());
            //BufferedInputStream bi=new BufferedInputStream(new InputStreamReader(new FileInputStream(file),"GBK"));
            oos = new ObjectOutputStream(client.getOutputStream());//client.getOutputStream()返回此套接字的输出流
//            //文件名、大小等属性
//            dos.writeUTF(file.getName());
//            dos.flush();
//            dos.writeLong(file.length());
//            dos.flush();
            // 开始传输文件
            System.out.println("======== 开始传输文件 ========");
//            byte[] bytes = new byte[1024];
//            int length = 0;
//
//            while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
//                dos.write(bytes, 0, length);
//                dos.flush();
//            }
            oos.writeObject(deployFiles);
            System.out.println("======== 文件传输成功 ========");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("客户端文件传输异常");
        }finally{
            fis.close();
            oos.close();
        }
    }

}
