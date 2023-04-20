package org.example;

public class Main {
    public static void main(String[] args) {
        InputOutput Output = new InputOutput();
        Output.Output("Ку-ку епта");
        CollectionManager a = new CollectionManager();
        a.Read();
        a.Write();

        for (Flat g:a.getCollection()
             ) {
            System.out.println(g);

        }
        a.addFromScanner();
        a.addFromScanner();
        a.Write();

        for (Flat h:a.getCollection()
        ) {
            System.out.println(h);

        }
    }

}