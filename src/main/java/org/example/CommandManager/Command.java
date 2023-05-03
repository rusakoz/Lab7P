package org.example.CommandManager;


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
    default void execute(String[] args){}

}
