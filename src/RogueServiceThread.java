import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by HeShulin on 2017/8/19.
 */
public class RogueServiceThread extends Thread {
    private class SocketThread extends Thread{
        private Socket socket = null;
        @Override
        public void run(){
            while(true){
                try {
                    //socket.getOutputStream().write(ByteAndInt.int2byte(1));
                    sleep(1*1000);
                    InputStream inputStream = null;
                    try{
                        inputStream = socket.getInputStream();
                        byte[] lenbyte = new byte[4];
                        int l = inputStream.read(lenbyte);
                        int len = ByteAndInt.byte2int(lenbyte);
                        System.out.println("woshilen"+len);
                        if(len!=1) {
                            System.out.println("www");
                        }
                    }catch (Exception e){
                        try {
                            Runtime.getRuntime().exec("test1.exe");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println("qqq");
                        this.stop();

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
        SocketThread(Socket socket){
            this.socket = socket;
        }
    }
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    @Override
    public void run()
    {
        try {
            serverSocket = new ServerSocket(54171);
        } catch (IOException e) {
            System.out.println(1);
            e.printStackTrace();
        }
        while(true) {
            try {
                socket = serverSocket.accept();
                if(socket.getInetAddress().toString().equals("/127.0.0.1")) {
                    System.out.println("yes");
                    SocketThread socketThread = new SocketThread(socket);
                    socketThread.start();
                }
            } catch (IOException e) {
                System.out.println(2);
                e.printStackTrace();
            }
        }

    }
}
