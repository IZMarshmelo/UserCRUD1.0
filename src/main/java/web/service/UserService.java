package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    public void saveUser(User user);

    public void updateUser(User updateUser);

    public void removeUserById(long id);

    public List<User> getAllUsers();

    public User getUserById(long id);

}