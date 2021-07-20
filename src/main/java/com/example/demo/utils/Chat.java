package com.example.demo.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "message", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sender")
    private String sender;
    @Column(name = "message")
    private String message;

    public Chat(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
}