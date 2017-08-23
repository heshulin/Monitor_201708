import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by HeShulin on 2017/8/19.
 */
public class DataThread extends Thread {
    @Override
    public void run(){
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            SocketByHsl.setSocketNull();
            SocketByHsl.setSocket("127.0.0.1", 5417);
            while (true) {

                BufferedImage bufferDesktop = robot.createScreenCapture(rectangle);
                //System.out.println("111");
                ByteArrayOutputStream byteDesktop = new ByteArrayOutputStream();
                //System.out.println("112");
                ImageIO.write(bufferDesktop, "jpg", byteDesktop);
                //System.out.println("113");
                int outsize = byteDesktop.toByteArray().length;
                byte[] tmpData = byteDesktop.toByteArray();
                System.out.println(ByteAndInt.int2byte(outsize).length);
                SocketByHsl.postData(ByteAndInt.int2byte(outsize));
                //System.out.println("114 "+outsize);
                //SocketByHsl.postData(outsize);
                SocketByHsl.postData(tmpData);
                System.out.println("发送成功");
            }

        } catch (Exception e) {
            try {
                System.exit(0);
                SocketByHsl.getSocket().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.stop();
            e.printStackTrace();
        }
    }
}
