import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer{
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(7007);
            //serverSocket.setSoTimeout(10000);
            while(true){
                System.out.println("Waiting for client on port "+ serverSocket.getLocalPort() + "...");
                Socket socket = serverSocket.accept();
                System.out.println("Just connected to " + socket.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true){
                    String line = fromClient.readLine();
                    System.out.println("recv:" + line);
                    toClient.println(line);
                    if(line.equals("quit")) break;
                }

            }
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }
}