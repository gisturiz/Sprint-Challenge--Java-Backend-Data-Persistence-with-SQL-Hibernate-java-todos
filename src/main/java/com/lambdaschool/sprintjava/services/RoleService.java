package com.lambdaschool.sprintjava.services;

import com.lambdaschool.sprintjava.models.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);
}