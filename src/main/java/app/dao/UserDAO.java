package app.dao;

public interface UserDAO {
    boolean login(int userId, String password);
}
