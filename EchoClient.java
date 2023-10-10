import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public static void main(String[] args) {
        try {
            int serverPort = 7007;
            InetAddress host = InetAddress.getByName("localhost");
            System.out.println("Connecting to server on port " + serverPort);

            Socket socket = new Socket(host, serverPort);
            System.out.println("Just Connected to " + socket.getRemoteSocketAddress());

            PrintWriter toServer = null;
            BufferedReader fromServer = null;
            while (true) {
                toServer = new PrintWriter(socket.getOutputStream(), true);
                fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in);
                String send = scanner.nextLine();

                toServer.println(send);
                //String line = fromServer.readLine();

                System.out.println("send:" + send);
                if (send.equals("quit")) break;
            }

            toServer.close();
            fromServer.close();
            socket.close();


        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

}
