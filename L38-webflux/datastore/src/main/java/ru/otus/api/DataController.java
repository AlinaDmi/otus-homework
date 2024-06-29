package ru.otus.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.domain.Message;
import ru.otus.domain.MessageDto;
import ru.otus.service.DataStore;

@RestController
public class DataController {
    private static final Logger log = LoggerFactory.getLogger(DataController.class);
    private final DataStore dataStore;
    private final Scheduler workerPool;

    public DataController(DataStore dataStore, Scheduler workerPool) {
        this.dataStore = dataStore;
        this.workerPool = workerPool;
    }

    @PostMapping(value = "/msg/{roomId}")
    public Mono<Long> messageFromChat(@PathVariable("roomId") String roomId, @RequestBody MessageDto messageDto) {
        if ("1408".equals(roomId)) {
            // Do not save messages in room 1408
            return Mono.empty();
        }

        var messageStr = messageDto.messageStr();
        var msgId = Mono.just(new Message(roomId, messageStr))
                .doOnNext(msg -> log.info("messageFromChat:{}", msg))
                .flatMap(dataStore::saveMessage)
                .publishOn(workerPool)
                .doOnNext(msgSaved -> log.info("msgSaved id:{}", msgSaved.getId()))
                .map(Message::getId)
                .subscribeOn(workerPool);

        log.info("messageFromChat, roomId:{}, msg:{} done", roomId, messageStr);
        return msgId;
    }

    @GetMapping(value = "/msg/{roomId}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<MessageDto> getMessagesByRoomId(@PathVariable("roomId") String roomId) {
        Flux<Message> messages;
        if ("1408".equals(roomId)) {
            // Load all messages for room 1408
            messages = dataStore.loadAllMessages();
        } else {
            messages = dataStore.loadMessages(roomId);
        }

        return messages
                .map(message -> new MessageDto(message.getMsgText()))
                .doOnNext(msgDto -> log.info("msgDto:{}", msgDto))
                .subscribeOn(workerPool);
    }


}
