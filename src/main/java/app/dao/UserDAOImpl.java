package app.dao;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean login(int userId, String password) {
        return userId == 1 && password.equals("password");
    }
}




