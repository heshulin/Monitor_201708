/**
 * Created by HeShulin on 2017/8/19.
 */
public class KeepDataThreadAlive extends Thread {
    private DataThread dataThread = null;
    @Override
    public void run()
    {
        while(true){
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!dataThread.isAlive())
            {
                DataThread dataThread = new DataThread();
                dataThread.start();
            }
        }
    }
    KeepDataThreadAlive(DataThread dataThread)
    {
        this.dataThread = dataThread;
    }
}
