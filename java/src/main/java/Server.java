import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) throws IOException {
        Socket s = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server Error.");
        }

        while(true) {
            s = serverSocket.accept();
            System.out.println("Połączono");
            ServerThread sT = new ServerThread(s);
            sT.start();
        }
    }
}

class ServerThread extends Thread {

    String txt = null;
    BufferedReader inputStream = null;
    PrintWriter outputStream = null;
    Socket s = null;
    User u = new User(null,null,null,null);
    MongoConnect mongo_connect = new MongoConnect();

    public ServerThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            inputStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outputStream = new PrintWriter(s.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            txt = inputStream.readLine();

            while(txt.compareTo("Z") != 0) {
                switch (txt){
                    case "R":
                        outputStream.println("Podaj swoje imie.");
                        outputStream.flush();
                        u.setName(inputStream.readLine());

                        outputStream.println("Podaj swoje nazwisko.");
                        outputStream.flush();
                        u.setSurname(inputStream.readLine());

                        outputStream.println("Podaj swoj email.");
                        outputStream.flush();
                        u.setEmail(inputStream.readLine());


                        outputStream.println("Podaj swoje haslo.");
                        outputStream.flush();
                        u.setPassword(inputStream.readLine());

                        if(mongo_connect.findUser(u)==true){
                            outputStream.println("Zostales poprawnie zarejestrowany. R - rejestracja. L - logowanie. Z - zakoncz.");
                            outputStream.flush();
                            mongo_connect.saveUser(u);
                        }else{
                            outputStream.println("Uzytkownik o podanych danych juz istnieje. R - rejestracja. L - logowanie. Z - zakoncz.");
                            outputStream.flush();
                        }
                        txt = inputStream.readLine();

                        break;
                    case "L":
                        outputStream.println("Podaj swoje imie.");
                        outputStream.flush();
                        u.setName(inputStream.readLine());

                        outputStream.println("Podaj swoje nazwisko.");
                        outputStream.flush();
                        u.setSurname(inputStream.readLine());

                        outputStream.println("Podaj swoj email.");
                        outputStream.flush();
                        u.setEmail(inputStream.readLine());


                        outputStream.println("Podaj swoje haslo.");
                        outputStream.flush();
                        u.setPassword(inputStream.readLine());

                        if(mongo_connect.findUser(u)!=true){
                            outputStream.println("Zostales poprawnie zalogowany.");
                            outputStream.flush();
                        }else{
                            outputStream.println("Nie ma uzytkownika o podanych danych. R - rejestracja. L - logowanie. Z - zakoncz.");
                            outputStream.flush();
                        }
                        txt = inputStream.readLine();
                        break;
                    default:
                        outputStream.println("Bledne polecenie. R - rejestracja. L - logowanie. Z - zakoncz.");
                        outputStream.flush();
                        txt = inputStream.readLine();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            txt = this.getName();
            System.out.println("Client zakończył połączenie" + txt);
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    System.out.println("Input Stream closed");
                }

                if (outputStream != null) {
                    outputStream.close();
                    System.out.println("Output Stream closed");
                }

                if (s != null) {
                    s.close();
                    System.out.println("Socket closed");
                }
            }
            catch (IOException e) {
                System.out.println("Socket close error!");
            }
        }
    }
}