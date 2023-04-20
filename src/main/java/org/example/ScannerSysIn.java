package org.example;

import lombok.Getter;

import java.util.Scanner;
@Getter
public class ScannerSysIn {
    Scanner scanner = new Scanner(System.in);
    public final void close(){
        scanner.close();
    }
}
