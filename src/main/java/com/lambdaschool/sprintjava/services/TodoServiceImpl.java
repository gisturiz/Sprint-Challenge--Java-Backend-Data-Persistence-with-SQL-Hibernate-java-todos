package com.lambdaschool.sprintjava.services;

import com.lambdaschool.sprintjava.models.ToDo;
import com.lambdaschool.sprintjava.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todorepos;

    @Override
    public List<ToDo> findAll()
    {
        List<ToDo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ToDo findTodoById(long id)
    {
        return todorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (todorepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (todorepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                todorepos.deleteById(id);
            }
            else
            {
                throw new EntityNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public ToDo save(ToDo toDo)
    {
        return todorepos.save(toDo);
    }

    @Transactional
    @Override
    public ToDo update(ToDo toDo, long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ToDo currentTodo = todorepos.findByDescription(authentication.getName());

        if (currentTodo != null)
        {
            if (id == currentTodo.getTodoid())
            {
                if (toDo.getDescription() != null)
                {
                    currentTodo.setDescription(toDo.getDescription());
                }

                if (toDo.getDatestarted() != null)
                {
                    currentTodo.setDatestarted(toDo.getDatestarted());
                }

                return todorepos.save(currentTodo);
            }
            else
            {
                throw new EntityNotFoundException(Long.toString(id) + " Not current todo");
            }
        }
        else
        {
            throw new EntityNotFoundException(authentication.getName());
        }

    }
}
