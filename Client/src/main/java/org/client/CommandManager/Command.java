package org.client.CommandManager;


import java.io.IOException;

/**
 * Интерфейс для комманд
 */
public interface Command {
    /**
     * Аргумент для команды
     */
    String Arg();

    /**
     * Описание команды
     */
    String Descr();

    /**
     *
     * @param args аргумент передачи массива строк для передачи аргумента команды
     */
    default void execute(String[] args) throws IOException, ClassNotFoundException {}

}
