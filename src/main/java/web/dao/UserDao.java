package web.dao;

import web.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void updateUser(@Valid User updateUser);

    void removeUserById(long id);

    List<User> getAllUsers();

    User getUserById(long id);

}