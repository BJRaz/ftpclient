/* FTPDataConnection 
 *
 *	BJR 18 jun 2007
 **/
package ftp.client;

import java.io.*;

public class FTPDataConnection extends FTPConnection {

    protected FTPDataConnection(String host, int port) {
        super(host, port);
    }

    public void getList() {
        try {
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            String res = "";
            while ((res = bin.readLine()) != null) {
                System.out.println(res);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getFile(String src) {
        //

        try {
            FileOutputStream writer = new FileOutputStream("C:\\Temp\\" + src);
            int c = 0;
            byte[] buf = new byte[1024];

            while ((c = in.read(buf)) != -1) {
                System.out.println(c);
                writer.write(buf, 0, c);
                writer.flush();
            }

            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
