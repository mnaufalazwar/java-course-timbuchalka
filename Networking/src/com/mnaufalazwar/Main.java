package com.mnaufalazwar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here

        try{
            URL url = new URL("http://example.org");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream())
            );

            String line = "";

            while (line != null){
                line = inputStream.readLine();
                System.out.println(line);
            }

            System.out.println("======================");

            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
            for(Map.Entry<String, List<String>> entry : headerFields.entrySet()){
                String key = entry.getKey();
                List<String> value = entry.getValue();
                System.out.println("-----key = " + key);
                System.out.println("value = " + value);
            }

            inputStream.close();
        } catch (MalformedURLException e){
            System.out.println("Malformed URL : " + e.getMessage());
        } catch (IOException e){
            System.out.println("IOException : " + e.getMessage());
        }

    }
}
