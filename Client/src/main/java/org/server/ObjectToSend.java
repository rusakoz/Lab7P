package org.server;

import java.io.Serial;
import java.io.Serializable;

public class ObjectToSend implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String nameCommand;
    private Object object;
    public ObjectToSend(String nameCommand, Object obj){
        this.nameCommand = nameCommand;
        this.object = obj;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public Object getObject() {
        return object;
    }
}
