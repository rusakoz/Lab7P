package org.server.Analyze;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.server.Main;
import org.server.ObjectToSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Analyzer {
    private static final Logger logger = LoggerFactory.getLogger(Analyzer.class);
    public ObjectToSend executor(ObjectInputStream ois)  {

        ObjectToSend kto = null;
        try {
            kto = (ObjectToSend) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Ошибка чтения объекта из ObjectInputStream");
            return new ObjectToSend("Команда не была выполнена", null);
        }

        Invoker invoker = new Invoker(new CollectionManager());
        if (kto != null) {
            return invoker.Invoke(kto);
        }else {
            logger.error("Объект не был сформирован");
            return new ObjectToSend("Команда не была выполнена", null);
        }
    }
}
