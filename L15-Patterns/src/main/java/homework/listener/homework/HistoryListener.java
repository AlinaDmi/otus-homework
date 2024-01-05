package homework.listener.homework;

import homework.listener.Listener;
import homework.model.Message;
import homework.model.ObjectForMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {
    private final HashMap<Long,Message> messageHistory = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        messageHistory.put(msg.getId(), msg.copy());
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(messageHistory.get(id));
    }
}
