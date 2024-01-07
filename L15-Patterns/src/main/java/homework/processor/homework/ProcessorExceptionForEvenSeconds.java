package homework.processor.homework;

import homework.model.Message;
import homework.processor.Processor;

public class ProcessorExceptionForEvenSeconds implements Processor {
    private final Timer timer;

    public ProcessorExceptionForEvenSeconds(Timer timer) {
        this.timer = timer;
    }

    @Override
    public Message process(Message message) {
        int second = timer.getTime().getSecond();

        if (second % 2 == 0) {
            throw new IllegalStateException("Exception for even second: " + second);
        }

        return message;
    }
}
