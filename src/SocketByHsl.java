import org.omg.PortableServer.POA;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by HeShulin on 2017/8/16.
 */
public class SocketByHsl {
    private static SocketByHsl socketByHsl = null;
    private static Socket socket = null;
    public static void setSocket(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
    }
    public static void setSocketNull() throws IOException {
        socket = null;
    }
    public static Socket getSocket() throws IOException {
        return socket;
    }
    public static synchronized SocketByHsl getSocketByHsl(){
        if(socketByHsl==null){
            socketByHsl = new SocketByHsl();
        }
        return socketByHsl;
    }
    public static void postData(byte[] tmpByte) throws IOException {
        socket.getOutputStream().write(tmpByte);
        socket.getOutputStream().flush();

    }
    public static void postData(int  tmpint) throws IOException {
        socket.getOutputStream().write(tmpint);
        socket.getOutputStream().flush();
    }
    private SocketByHsl(){};

}
