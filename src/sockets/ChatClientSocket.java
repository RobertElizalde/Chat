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
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class ChatClientSocket extends java.net.Socket {
    
    protected boolean turn = false;
    
    private InputStream in;
    private OutputStream out;
    
    public ChatClientSocket(InetAddress address, int port) throws IOException {
        super(address, port);
    }
    
    public ChatClientSocket(String address, int port) throws IOException {
        super(address, port);
    }
    
    public void startChat(String name) {
        try {
            System.out.println(this.getRemoteSocketAddress());
            in = this.getInputStream();
            out = this.getOutputStream();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out);
            
            do {
                
                String inMessage = null;
                do {
                    System.out.print("<<< ");
                    inMessage = reader.readLine();
                    System.out.println(inMessage);
                } while(!inMessage.contains(";"));
                
                String outMessage = null;
                do {

                   
                        System.out.print(">>> ");
                        outMessage = new Scanner(System.in).nextLine();
                    

                    writer.println(name + ": " + outMessage);
                    writer.flush();
                } while(!outMessage.contains(";"));
                
            } while(true);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
