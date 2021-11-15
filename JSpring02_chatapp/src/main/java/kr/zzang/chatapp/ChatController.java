package kr.zzang.chatapp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@RequiredArgsConstructor
// data return server
@RestController
public class ChatController {

    private final ChatRepository chatRepository;

    @RequestMapping(value="/sender/{sender}/receiver/{receiver}",
            // SSE 프로토콜
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            method=RequestMethod.GET)
    public Flux<Chat> getMsg(@PathVariable String sender,
                             @PathVariable String receiver) {
        return chatRepository.mFindBySender(sender, receiver)
                .subscribeOn(Schedulers.boundedElastic());
    }

    // Mono는 data 1번만 return
    // Flux는 data 여러번 return
    // type을 void로 해도됨
    @RequestMapping(value="/chat")
    public Mono<Chat> setMsg(@RequestBody Chat chat) {
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
    }
}
