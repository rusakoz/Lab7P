package org.client.CommandManager.CreateObjectForCollection;

import java.io.Serial;
import java.io.Serializable;

public class ObjectToSend implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String nameCommand;
    private Object object;
    public ObjectToSend(String nameCommand, Object object){
        this.nameCommand = nameCommand;
        this.object = object;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public Object getObject() {
        return object;
    }
}
