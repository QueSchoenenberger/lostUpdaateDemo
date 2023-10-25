import DTO.BankAccountDAO;
import Model.BankAccount;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/demo_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws InterruptedException {
        Main program = new Main();
        program.runDemoTransactions();
    }


    private void runDemoTransactions() throws InterruptedException {
        Thread session1 = new Thread(() -> {
            try {
                transaction1();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Thread session2 = new Thread(() -> {
            try {
                transaction2();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // run transactions
        session1.start();
        session2.start();

        session1.join();
        session2.join();
    }

    private void transaction1() throws SQLException {
        Connection connection = openNewConnection(); // start separate session
        try {
            BankAccountDAO dao = new BankAccountDAO(connection);
            BankAccount account = dao.getBankAccount(1); // Read


            BigDecimal newBalance = account.getBalance().add(new BigDecimal("15.00"));
            account.setBalance(newBalance);
            dao.updateBankAccount(account); // Write

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.close(); // end
        }
    }

    private void transaction2() throws SQLException {
        Connection connection = openNewConnection(); // start separate session
        try {
            BankAccountDAO dao = new BankAccountDAO(connection);
            BankAccount account = dao.getBankAccount(1); // Read


            BigDecimal newBalance = account.getBalance().subtract(new BigDecimal("25.00"));
            account.setBalance(newBalance);
            dao.updateBankAccount(account); // Write

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.close(); // end
        }
    }


    private Connection openNewConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}
