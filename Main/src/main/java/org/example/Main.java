package org.example;

import org.example.CommandManager.*;

/**
 * Класс запуска программы
 * @author Ruslan and Alexander
 */
public class Main {
    public static void main(String[] args) {
        CollectionManager a = new CollectionManager();
        a.Read();
        //a.Write();

        //for (Flat g:a.getCollection()
             //) {
            //System.out.println(g);
        //}

        Invoker invoker = new Invoker(a);
        invoker.Invoke();

        //a.Write();

        //for (Flat h:a.getCollection()
        //) {
            //System.out.println(h);
        //}
    }

}