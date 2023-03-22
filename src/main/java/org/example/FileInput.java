package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput implements IO {
    private File file;
    private Scanner scanner;

    public FileInput(String pathName){

        File file = new File(pathName);
        this.file = file;
        try {
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            this.file = new File(System.getenv(pathName));
            try {
                this.scanner = new Scanner(this.file);
            } catch (FileNotFoundException ex) {
                Output("File not found in specified directory and in PATH, check the path or name you specified.");
            }
        }
    }

}
