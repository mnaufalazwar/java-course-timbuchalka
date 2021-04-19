package com.mnaufalazwar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //127.0.0.1 if localhost doesn't work
        try(Socket socket = new Socket("localhost", 5000)){
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String sendString;
            String response;

            do{
                System.out.println("Enter String : ");
                sendString = scanner.nextLine();

                writer.println(sendString);

                if(!sendString.equals("exit")){
                    response = reader.readLine();
                    System.out.println(response);
                }
            } while (!sendString.equals("exit"));

        } catch (IOException e){
            System.out.println("Client error : " + e.getMessage());
        }

    }
}
