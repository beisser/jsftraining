package com.github.beisser.service;

import com.github.beisser.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Nico on 13.08.2016.
 */
@ApplicationScoped
public class UserService {

    @Inject
    private DbService dbService;

    public List<User> findAll() throws Exception {
        return dbService.findAll();
    }

    public String save(User user) throws Exception{
        if (user.getId() == 0) {
            dbService.addUser(user);
        } else {
            dbService.updateUser(user);
        }
        return "users";
    }

    public User findById(int id) throws Exception{
        return dbService.findById(id);
    }

    public void delete(int id) throws Exception {
        dbService.delete(id);
    }
}
