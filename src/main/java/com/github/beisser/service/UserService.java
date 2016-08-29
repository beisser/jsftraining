package com.github.beisser.service;

import com.github.beisser.model.User;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Nico on 13.08.2016.
 */
@ApplicationScoped
public class UserService {

    @Inject
    private DbServiceJPA dbService;

    public List<User> findAll() throws Exception {
        List<User> result = dbService.findAll(User.class);
        return result;
    }

    public String save(User user) throws Exception{
        if (user.getId() == 0) {
            dbService.add(user);
        } else {
            dbService.update(user);
        }
        return "users";
    }

    public User findById(int id) throws Exception{
        User result = dbService.findById(User.class,id);
        return result;
    }

    public void delete(int id) throws Exception {
        User userToDelete = this.findById(id);
        if (userToDelete != null) {
            dbService.delete(userToDelete);
        }
    }
}
