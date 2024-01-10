package businessLogic;
import dataAccess.UserDAO;
import java.sql.SQLException;
public class LoginService {
    private UserDAO userDAO;

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authenticateUser(String username, String password, String userType) {
        try {
            return userDAO.authenticateUser(username, password, userType);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
