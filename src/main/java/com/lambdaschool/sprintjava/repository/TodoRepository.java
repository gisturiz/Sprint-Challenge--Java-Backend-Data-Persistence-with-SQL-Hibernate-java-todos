package com.lambdaschool.sprintjava.repository;

import com.lambdaschool.sprintjava.models.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, Long>
{
    ToDo findByDescription(String description);
}
