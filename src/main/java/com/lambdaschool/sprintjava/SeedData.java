package com.lambdaschool.sprintjava;

import com.lambdaschool.sprintjava.models.Role;
import com.lambdaschool.sprintjava.models.ToDo;
import com.lambdaschool.sprintjava.models.User;
import com.lambdaschool.sprintjava.models.UserRoles;
import com.lambdaschool.sprintjava.repository.RoleRepository;
import com.lambdaschool.sprintjava.repository.TodoRepository;
import com.lambdaschool.sprintjava.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    TodoRepository todorepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, TodoRepository todorepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.todorepos = todorepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("users");
        Role r3 = new Role("writer");
        Role r4 = new Role("reader");
        Role r5 = new Role("added");

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> writer = new ArrayList<>();
        writer.add(new UserRoles(new User(), r3));

        ArrayList<UserRoles> reader = new ArrayList<>();
        reader.add(new UserRoles(new User(), r4));

        ArrayList<UserRoles> added = new ArrayList<>();
        added.add(new UserRoles(new User(), r5));

        rolerepos.save(r1);
        rolerepos.save(r2);
        rolerepos.save(r3);
        rolerepos.save(r4);
        rolerepos.save(r5);

        User u1 = new User("barnbarn", "password", users);
        User u2 = new User("admin", "password", admins);
        User u3 = new User("Bob", "password", writer);
        User u4 = new User("Jane", "password", reader);

        // the date and time string should get converted to a datetime Java data type. This is done in the constructor!
        u4.getTodos().add(new ToDo("Finish java-orders-swagger", "2019-01-13 04:04:04", u4));
        u4.getTodos().add(new ToDo("Feed the turtles", "2019-03-01 04:04:04", u4));
        u4.getTodos().add(new ToDo("Complete the sprint challenge", "2019-02-22 04:04:04", u4));

        u3.getTodos().add(new ToDo("Walk the dogs", "2019-01-17 04:04:04", u3));
        u3.getTodos().add(new ToDo("provide feedback to my instructor", "2019-02-13 04:04:04", u3));

        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);
        userrepos.save(u4);
    }
}
