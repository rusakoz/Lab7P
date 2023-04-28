package org.example.CommandManager;



public interface Command {
    String Arg();
    String Descr();
    default void execute(String[] args){}
    //default void execute(){}

}
