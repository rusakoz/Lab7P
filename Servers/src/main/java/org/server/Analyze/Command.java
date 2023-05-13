package org.server.Analyze;


import org.server.ObjectToSend;

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
     * @param objectToSend аргумент передачи объекта
     */
    default void execute(ObjectToSend objectToSend){}

}
