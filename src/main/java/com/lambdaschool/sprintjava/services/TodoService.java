package com.lambdaschool.sprintjava.services;

import com.lambdaschool.sprintjava.models.ToDo;

import java.util.List;

public interface TodoService
{
    List<ToDo> findAll();

    ToDo findTodoById(long id);

    void delete(long id);

    ToDo save(ToDo toDo);

    ToDo update(ToDo toDo, long id);
}
