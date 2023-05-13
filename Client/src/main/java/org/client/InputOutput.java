package org.client;

/**
 * Класс вывода на экран
 */
public class InputOutput implements IO{
    /**
     * Конструктор
     */
    public InputOutput(){}

    /**
     * Метод реализует вывод информации на экран
     * @param msg строка для вывода на экран
     */
    @Override
    public void Output(String msg) {
        System.out.println(msg);
    }

    @Override
    public void OutputErr(String msg){System.err.println(msg);}
}
