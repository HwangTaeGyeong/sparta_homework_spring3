package com.sparta.todoapp.controller;


import java.util.List;
import java.util.stream.Collectors;

import com.sparta.todoapp.CommonResponse;
import com.sparta.todoapp.repository.Comment;
import com.sparta.todoapp.repository.Todo;
import com.sparta.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping("/todo")
@RestController
@AllArgsConstructor
public class TodoController {

    public final TodoService todoService;

    //할일생성
    @PostMapping
    public ResponseEntity<CommonResponse<TodoResponseDTO>> postTodo(@RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.createTodo(dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<TodoResponseDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("생성이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    //할일 단건 조회
    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDTO>> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<TodoResponseDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("단건 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    //할일 전체 조회
    @GetMapping
    public ResponseEntity<CommonResponse<List<TodoResponseDTO>>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDTO> response = todos.stream()
                .map(TodoResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<TodoResponseDTO>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("목록 조회이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    //할일 수정
    @PutMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDTO>> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.updateTodo(todoId, dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok()
                .body(CommonResponse.<TodoResponseDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("수정이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    //할일 삭제
    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        todoService.deleteTodo(todoId, dto.getPassword());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제가 완료 되었습니다.")
                .build());
    }

    @PostMapping("/{todoId}/comments")
    public Comment addComment(@PathVariable Long todoId, @RequestBody Comment comment) {
        return todoService.addComment(todoId, comment.getContent(), comment.getUserId());
    }

    @GetMapping("/{todoId}/comments")
    public List<Comment> getCommentsByTodoId(@PathVariable Long todoId) {
        return todoService.getCommentsByTodoId(todoId);
    }
}
