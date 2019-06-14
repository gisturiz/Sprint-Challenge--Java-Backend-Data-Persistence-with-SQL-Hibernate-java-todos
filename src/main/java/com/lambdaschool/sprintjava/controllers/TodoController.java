package com.lambdaschool.sprintjava.controllers;

import com.lambdaschool.sprintjava.models.ToDo;
import com.lambdaschool.sprintjava.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController
{
    @Autowired
    TodoService todoService;

    @PutMapping(value = "/todos/todoid/{id}")
    public ResponseEntity<?> updateTodo(@RequestBody ToDo updateTodo, @PathVariable long id)
    {
        todoService.update(updateTodo, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}