package org.server;

import java.io.Serial;
import java.io.Serializable;

public class ObjectToSend implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String info;
    private Object object;
    public ObjectToSend(String nameCommand, Object obj){
        this.info = nameCommand;
        this.object = obj;
    }

    public String getNameCommand() {
        return info;
    }

    public Object getObject() {
        return object;
    }
}
