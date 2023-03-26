package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CSVReader {

    public void Read(){

        StringBuilder str = new StringBuilder();

        Dotenv dotenv = Dotenv.load();
        String path = dotenv.get("HELLO");

        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)){

            int data = reader.read();
            while(data != -1){
                str.append((char)data);
                data = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
