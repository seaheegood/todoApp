package com.sparta.todoapp.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    private String title;

    private String content;

    private String userName;

    private String password;

    private LocalDateTime createdAt;

    @Builder
    public Todo(String title, String content, String userName, String password) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }
}
