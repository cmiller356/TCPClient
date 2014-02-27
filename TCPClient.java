
/***********
Author: Christian Miller
Class: CIS4443
Program Requirements: Create a Java program similar to the Java TCP Client program on the G drive.
Your program should accept a single properly formed URL (without the protocol
specifier, examples: www.sbuniv.edu, 10.0.65.10/~jcain,
10.0.65.10/~jcain/index.html).  Your program should then create a HTTP 1.1 GET
statement appropriate for that URL and transmit it to the server specified in
the URL.  Your program should then print to standard output everything it
receives back from the server.  Your program should not expect any input other
than that single URL and should automatically end on its own after it has
printed everything it received from the server for a single URL.

Note: You are printing the raw responses, both HTTP and HTML, from the server
and are NOT rendering a web page like a web browser would do.
***********/

/**  The java.net package contains the basics needed for network operations. */
import java.net.*;
/** The java.io package contains the basics needed for IO operations. */
import java.io.*;
import java.util.*;

public class TCPClient{
public static void main(String argv[]) throws Exception {
    String modifiedSentence;
    /** Define a host server */
    System.out.println("Please enter an address: ");
    Scanner scan = new Scanner(System.in);
    String userInput = scan.nextLine();
    URL host =  new URL("http:/" + "/" + userInput + "/");
    /** Define a port */
    int port = 80;
    StringBuffer instr = new StringBuffer();
    String TimeStamp;
    System.out.println("SocketClient initialized");
        try {
            /**Establish a socket connetion */
            Socket connection = new Socket(host.getHost(), port);
            /** Instantiate a DataOutputStream object */
            DataOutputStream outToServer = new DataOutputStream(connection.getOutputStream());
            /** Instantiate a BufferedReader object for reading incoming socket streams. */
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        /** Creates an HTTP 1.1 GET statement for the URL input and transmit to the specified server. */
                        String getRequest =  "GET " + host.getPath() + " HTTP/1.1\nHost: " + host.getHost() + "\nConnection: close\n\n";
            outToServer.writeBytes(getRequest);
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER:" + modifiedSentence);
            String fromServer;
            do{
                            fromServer = inFromServer.readLine();
                if(fromServer != null){
                                System.out.println(fromServer);
                                }
            } while(fromServer != null);
            /** Close the socket connection. */
            connection.close();
        }//try

    catch (IOException f) {
      System.out.println("IOException: " + f);
    }//catch
    catch (Exception g) {
      System.out.println("Exception: " + g);
    }//catch
  }//main
}//TCPClient
