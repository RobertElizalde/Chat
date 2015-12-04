/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class ChatServerSocket extends java.net.ServerSocket {
    
    private Socket chatClientSocket;
    
    private InputStream in;
    private OutputStream out;
    
    public ChatServerSocket(int port) throws IOException{
        super(port);
    }
    
    public void startChat(String name) {
        try {
            chatClientSocket = this.accept();
            System.out.println("Connexion acpetada");
            in = chatClientSocket.getInputStream();
            out = chatClientSocket.getOutputStream();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out);
            
            do {
                String outMessage = null;
                do {

                    
                        System.out.print(">>> ");
                        outMessage = new Scanner(System.in).next();
                    writer.println(name + ": " +outMessage);
                    writer.flush();
                } while(!outMessage.contains(";"));
                
                
                String inMessage = null;
                do {
                    System.out.print("<<< ");
                    inMessage = reader.readLine();
                    System.out.println(inMessage);
                } while(!inMessage.contains(";"));
            } while(true);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
