package org.server.Analyze;

/**
 * Интерфейс ввода/вывода
 */
public interface IO {
    /**
     *
     * @param msg строка для вывода на экран
     */
    default void Output(String msg){}

    /**
     *
     * @param msg строка для вывода на экран
     */
    default void OutputErr(String msg){}
}
