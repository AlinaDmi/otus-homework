package homework.processor.homework;

import homework.model.Message;
import homework.processor.Processor;

public class ProcessorReplaceFields implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder().field11(message.getField12()).field12(message.getField11()).build();
    }
}
