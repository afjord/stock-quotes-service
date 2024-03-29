package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.User;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

public interface UserDAO {
  void addUser(User user);

  User getUser(long id);

  void updateUser(User user);

  void deleteUser(long id);

  void addOrUpdateUser(User user);
}
