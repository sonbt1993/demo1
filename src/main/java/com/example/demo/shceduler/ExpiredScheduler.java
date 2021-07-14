//package com.example.demo.shceduler;
//
//import com.example.demo.repository.ChatRepository;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.slf4j.Logger;
//
//@Component
//@RequiredArgsConstructor
//public class ExpiredScheduler {
//
//    private final ChatRepository chatRepository;
//    @Scheduled(fixedDelay = 1 * 1000 * 60)
//    public void postTime(){
//        chatRepository.save(new Chat("Bot", "Bây giờ là: " + java.time.LocalDateTime.now()));
//    }
//    @Scheduled(cron = "0 0 12 * * ?")
//    public void postHaveLaunch(){
//        chatRepository.save(new Chat("Bot", "Đi ăn trưa thôi :)))"));
//    }
//
//}
