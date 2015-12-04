/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sockets.ChatClientSocket;
import sockets.ChatServerSocket;

/**
 *
 * @author Alumno
 */
public class Chat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("(1) Servidor\n (2) Cliente");
        int opcion = new Scanner(System.in).nextInt();
        System.out.println("Introduce tu nombre");
        String nombre = new Scanner(System.in).next();
        switch(opcion) {
            case 1:
                System.out.print("Puerto: ");
                int puerto = new Scanner(System.in).nextInt();
                    {
                        try {
                            ChatServerSocket server = new ChatServerSocket(puerto);
                            server.startChat(nombre);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                break;
            case 2:
                System.out.print("Puerto: ");
                int puertoc = new Scanner(System.in).nextInt();
                System.out.println("IP(l- local): ");
                String ip = new Scanner(System.in).next();
                ChatClientSocket client = null;
                if(ip.equals("l")) {
                    client = new ChatClientSocket(InetAddress.getLocalHost(), puertoc);
                } else {
                    
                }
                client.startChat(nombre);
                break;
            default:
                System.err.println("Opcion invalida!!!");
                break;
        }
    }
    
}
