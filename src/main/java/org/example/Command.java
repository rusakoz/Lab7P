package org.example;

public interface Command {
    default void Descr(){}
    default void execute(String[] args){}
    //default void execute(){}

}
