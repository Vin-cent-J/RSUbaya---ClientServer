/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rsubayaservertcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Vincent
 */
public class HandleSocketRSUBAYA  extends Thread{
    Socket client;
    DataOutputStream out;
    BufferedReader in;
    UIServerRSUBAYA parent;
    
    public HandleSocketRSUBAYA(UIServerRSUBAYA parent, Socket client){
        this.parent = parent;
        this.client = client;
        
        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (Exception e) {
            System.out.println("HandleSocket error");
        }
    }
    
    public void kirimPesan(String pesan){
        try {
            out.writeBytes(pesan);
        } catch (Exception e) {
            System.out.println("Kirim Pesan error");
        }
    }
    
     @Override
    public void run(){
        while(true){//siap menerima pesan dari client
            try {
                String data = in.readLine();
                parent.showPasien(data, client);
            } catch (Exception e) {
                System.out.println("run error: " + e);
            }
        }
    }
}
