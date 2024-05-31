package com.sparta.todoapp.repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private String userId;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    @JsonBackReference
    private Todo todo;

    // 기본 생성자
    public Comment() {
    }

    // 매개변수 있는 생성자
    public Comment(String content, String userId, LocalDateTime createdAt, Todo todo) {
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.todo = todo;
    }
}