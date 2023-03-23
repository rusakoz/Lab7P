package org.example;

import io.github.cdimascio.dotenv.Dotenv;
public class Main {
    public static void main(String[] args) {
        InputOutput Output = new InputOutput();
        Output.Output("Ку-ку епта");
        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("HELLO"));
    }
}