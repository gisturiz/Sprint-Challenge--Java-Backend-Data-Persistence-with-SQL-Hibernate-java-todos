package com.lambdaschool.sprintjava.repository;

import com.lambdaschool.sprintjava.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
