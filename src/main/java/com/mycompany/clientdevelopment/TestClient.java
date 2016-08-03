/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientdevelopment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Богдан
 */
public class TestClient implements Runnable {
    
    public TestClient() {
    }
    
    @Override
    public void run() {
        System.out.println("Thread run");
        while(true){
            try (Socket client = new Socket(InetAddress.getLocalHost(), 9060)) {
            System.out.println("CONNECTED!");
            
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            oos.writeUTF("PING");
            String response = ois.readUTF();
                System.out.println("blabla");         
            switch (response) {
                case "PONG":
                    oos.writeUTF("PING");
                    oos.flush();
                    break;
                case "TIME":
                    if (ois.readUTF().matches("%2d:%2d:%2d")) {
                        oos.writeUTF(response);
                        oos.flush();
                    }
                default:
                    throw new AssertionError();
            }
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        }
    
}
