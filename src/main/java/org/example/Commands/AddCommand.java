package org.example.Commands;

import org.example.CollectionManager;
import org.example.Command;

public class AddCommand implements Command {
    CollectionManager cm;
    public AddCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        cm.addFromScanner();
    }
}
