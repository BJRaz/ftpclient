/* FTPCommandConnection */
package ftp.client;

import java.io.*;
import java.net.*;

public class FTPCommandConnection extends FTPConnection {

    private PrintWriter pw;
    private int remoteport;

    protected FTPCommandConnection(String host, int port) {
        super(host, port);
    }

    public void initialize() {
        try {
            server = new Socket(host, port);
            in = server.getInputStream();
            out = server.getOutputStream();

            pw = new PrintWriter(out, true);

            new Thread(new Runnable() {

                public void run() {
                    try {
                        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                        String res = "";
                        while ((res = bin.readLine()) != null) {
                            //System.out.println(res);
                            parseResponse(res);
                            synchronized (FTPCommandConnection.this) {
                                FTPCommandConnection.this.notifyAll();
                            }

                        }
                    } catch (Exception e) {

                    }
                }

            }).start();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void sendCommand(String command) {

        if (!pw.checkError()) {

            try {
                System.out.println(">> " + command);
                pw.println(command);

            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.out.println("Error in sending message");
        }

    }

    public int getRemotePort() {
        return remoteport;
    }

    private void parseResponse(String response) {

        int responseCode = Integer.parseInt(response.substring(0, 3));
        //System.out.println(responseCode);
        switch (responseCode) {
            case 220:	//welcome
                System.out.println("<< " + response);
                break;
            case 227:	//pasv response

                String res;
                res = (res = response.substring(response.indexOf("("), response.indexOf(")"))).substring(1, res.length());
                String[] r = res.split(",");

                remoteport = Integer.parseInt(r[5]) + (Integer.parseInt(r[4]) * 256);

                System.out.println("<< " + response);
                break;
            case 230:	//login ok
                System.out.println("<< " + response);
                break;
            case 331:
                System.out.println("<< " + response);

                break;
            case 500:	//Error
                System.out.println("<<ERROR " + response);
                break;

            default:
                System.out.println("<< " + response);

        }
    }
}
