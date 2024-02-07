package com.sparta.todoapp.service;

import com.sparta.todoapp.controller.TodoRequestDto;
import com.sparta.todoapp.repository.Todo;
import com.sparta.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 할일 생성
    public Todo createTodo(TodoRequestDto dto) {
        var newTodo = dto.toEntity();
        todoRepository.save(newTodo);
        return todoRepository.save(newTodo);
    }

    // 할일 단건 조회
    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 할일 전체 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }

    // 할일 수정
    public Todo updateTodo(Long todoId, TodoRequestDto dto) {
        Todo todo = getTodo(todoId);

        // 비밀번호 체크
        if (todo.getPassword() != null && !Objects.equals(todo.getPassword(), dto.getPassword())) {
            throw new IllegalArgumentException();
        }

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName(dto.getUserName());

        return todoRepository.save(todo);
    }
}
