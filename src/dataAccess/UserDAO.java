package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean authenticateUser(String username, String password, String userType) throws SQLException {
        String query = "SELECT * FROM users WHERE username=? AND password=? AND user_type=?";
        boolean isValid = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                isValid = resultSet.next(); // If a row is found, credentials are valid
            }
        }
        return isValid;
    }
}

