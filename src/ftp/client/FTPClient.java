/* FTPClient */
package ftp.client;

public class FTPClient {

    private FTPCommandConnection conn;
    private int port = 21;
    private String host;

    public FTPClient() {
    }

    public void connect(String host, int port) {
        this.host = host;
        this.port = port;
        conn = new FTPCommandConnection(host, port);
    }

    public void connect(String host) {
        this.host = host;
        conn = new FTPCommandConnection(host, port);
    }

    public void disconnect() {
        conn.close();
    }

    public void list() {

        conn.sendCommand("LIST");

        FTPDataConnection data = new FTPDataConnection(host, conn.getRemotePort());
        data.getList();
        data.close();
    }

    public void parent() {
    }

    public void cd() {
    }

    public void download(String dest) {

        FTPDataConnection data = new FTPDataConnection(host, conn.getRemotePort());
        data.getFile(dest);
        data.close();
    }

    public void upload() {
    }

    public void mode(int mode) {
        try {
            synchronized (conn) {
                if (mode == 1) {
                    conn.sendCommand("TYPE I");
                    conn.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retr(String src) {
        try {
            synchronized (conn) {
                conn.sendCommand("RETR " + src);
                download(src);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pasv() {
        try {
            synchronized (conn) {
                conn.sendCommand("PASV");
                conn.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void port() {
        conn.sendCommand("PORT");
    }

    public void send(String test) {
        try {
            synchronized (conn) {
                conn.sendCommand(test);
                conn.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
