package com.sparta.todoapp.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "todoId", nullable = false)
    private Long todoId;

    private String title;
    private String content;
    private String userName;
    private String password;
    private LocalDateTime createdAt;

    // 기본 생성자
    public Todo() {
    }

    @Builder
    public Todo(String title, String content, String userName, String password) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
