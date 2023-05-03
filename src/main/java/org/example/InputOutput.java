package org.example;

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
}
