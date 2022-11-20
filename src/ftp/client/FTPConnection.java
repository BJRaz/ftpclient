/* FTPConnection
 *	
 *	abstract class
 **/
package ftp.client;

import java.io.*;
import java.net.*;

public abstract class FTPConnection {

    protected InputStream in;
    protected OutputStream out;
    protected Socket server;
    protected int port;
    protected String host;

    protected FTPConnection(String host, int port) {
        this.host = host;
        this.port = port;

        initialize();
    }

    protected void initialize() {
        try {
            server = new Socket(host, port);
            in = server.getInputStream();
            out = server.getOutputStream();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    protected void close() {
        try {
            server.close();
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
