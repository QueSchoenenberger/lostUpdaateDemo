package DTO;

import Model.BankAccount;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDAO {

    Connection connection;
    private static final String SELECT_QUERY = "SELECT * FROM bank_account WHERE account_id = ?";
    private static final String UPDATE_QUERY = "UPDATE bank_account SET balance = ? WHERE account_id = ?";

    public BankAccountDAO(Connection connection) {
        this.connection = connection;
    }

    public BankAccount getBankAccount(int accountId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                return new BankAccount(id, balance);
            }
        }
        return null;
    }

    public void updateBankAccount(BankAccount account) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setBigDecimal(1, account.getBalance());
            statement.setInt(2, account.getAccountId());
            statement.executeUpdate();
        }
    }
}
