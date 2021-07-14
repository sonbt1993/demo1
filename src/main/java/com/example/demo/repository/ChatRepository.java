package com.example.demo.repository;

import com.example.demo.shceduler.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "chatRepository")
public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
