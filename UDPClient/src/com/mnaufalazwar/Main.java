package com.mnaufalazwar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try{
            InetAddress address = InetAddress.getLocalHost(); //getByName();
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String echoString;

            do{
                System.out.println("Enter string : ");
                echoString = scanner.nextLine();

                byte[] buffer = echoString.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                datagramSocket.send(packet);
            } while (!echoString.equals("exit"));
        } catch(SocketException e){
            System.out.println("SocketException : " + e.getMessage());
        } catch(IOException e){
            System.out.println("IOException : " + e.getMessage());
        }
    }
}
