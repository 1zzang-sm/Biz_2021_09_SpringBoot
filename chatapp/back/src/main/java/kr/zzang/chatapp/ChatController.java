package kr.zzang.chatapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
// data return server
@RestController
public class ChatController {

    private final ChatRepository chatRepository;

    // 귓말용
    @CrossOrigin
    @RequestMapping(value="/sender/{sender}/receiver/{receiver}",
            // SSE 프로토콜
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            method=RequestMethod.GET)
    public Flux<Chat> getMsg(@PathVariable String sender,
                             @PathVariable String receiver) {
        log.debug("sender: {}", sender);
        log.debug("receiver : {}", receiver);
        return chatRepository.mFindBySender(sender, receiver)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @CrossOrigin
    @RequestMapping(value="/chat/roomNum/{roomNum}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> findByRoomNum(@PathVariable Integer roomNum) {
        return chatRepository.mFindByRoomNum(roomNum)
                .subscribeOn(Schedulers.boundedElastic());
    }

    // Mono는 data 1번만 return
    // Flux는 data 여러번 return
    // type을 void로 해도됨
    @CrossOrigin
    @RequestMapping(value="/chat", method=RequestMethod.POST)
    public Mono<Chat> setMsg(@RequestBody Chat chat) {
        chat.setCreatedAt(LocalDateTime.now());
        // spring은 object를 리턴하면 자동으로 JSON 변환한다
        // (MessageConverter 에 의해서)
        return chatRepository.save(chat);
    }
}
