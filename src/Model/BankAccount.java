package Model;

import java.math.BigDecimal;

public class BankAccount {
    private int accountId;
    private BigDecimal balance;

    public BankAccount(int accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

