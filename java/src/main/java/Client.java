import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main (String args[]) throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        Socket userSocket = null;
        String txt = null;
        String response = null;
        PrintWriter outputStream = null;
        BufferedReader inputStream = null;
        BufferedReader br = null;

        try {
            userSocket = new Socket(address, 4444);
            br = new BufferedReader(new InputStreamReader(System.in));

            inputStream = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            outputStream = new PrintWriter(userSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println("Szukasz drugiej polowki, Witaj w naszej aplikacji.");
        System.out.println("Co chcesz zrobic?");
        System.out.println("R - rejestracja");
        System.out.println("L - logowanie");
        System.out.println("Z - zakoncz");



        try {
            txt = br.readLine();
            outputStream.println(txt);
            outputStream.flush();

            while(txt.compareTo("Z") != 0) {
                txt = inputStream.readLine();
                System.out.println(txt);
                txt = br.readLine();
                outputStream.println(txt);
                outputStream.flush();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            inputStream.close();
            outputStream.close();
            br.close();
            userSocket.close();
            System.out.println("Połączenie zakończone.");
        }
    }
}