/**/
import java.io.*;

public class Tester {

    public static void main(String[] args) {

        try {
            BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));

            /*OutputStream out = System.out;
 				
			int x=0;
				
			out.write(9);
			
			out.write(48);
					
			out.write(15);
					
			//out.write(10);
					
			out.flush();

			System.exit(1);
             */
            ftp.client.FTPClient client = new ftp.client.FTPClient();
            client.connect("ftp.gnome.org", 21);

            String res = "";
            //while((res = bin.readLine()) != null) {
            res = bin.readLine();
            client.send("user anonymous");

            //res = bin.readLine();
            //client.send("pass bb1975kewl");
            res = bin.readLine();
            client.send("pass " + res.toString());
            // pasv
            client.pasv();

            // list
            client.list();

            /*
	 			res = bin.readLine();
	 			int port = Integer.parseInt(res);
	 			client.testData(port);
             */
            client.mode(1);

            client.pasv();

            res = bin.readLine();
            client.retr(res);

            client.disconnect();
            //}	
        } catch (Exception e) {

        }

    }

}
