package client;

import java.io.*;

public class ClientReaderDelegator extends Thread
{
    private String inputValue;
    private boolean keepRunning;
    private BufferedReader in;
    private static ClientReaderDelegator instance=null;


    public static ClientReaderDelegator getInstance() {
    	if(instance==null) {
    		synchronized (ClientReaderDelegator.class) {
    			instance=new ClientReaderDelegator();
    			instance.start();
			}
    	}
    	return instance;
    }
    
    private ClientReaderDelegator() {
        super("ClientReaderDelegator");
        inputValue = null;
        keepRunning = true;
		in = new BufferedReader(new InputStreamReader(System.in));
    }

    public synchronized String waitForInput() {
        String rv = null;
        try {
            wait();
        } catch (InterruptedException e) {
            return null;
        }
        rv = new String(inputValue);
        return rv;
    }

    public void run() {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));

			while (keepRunning) {
            	try {
					inputValue = in.readLine();
				} catch (Exception e) {
					return;
				}
				synchronized (this) {
				notifyAll();
				}
			}
    }
    
    public void stopRunning() {
    	keepRunning = false;
    	try {
    		in.close();
    	} catch (IOException e) {
    	}
    	interrupt();
    }
}