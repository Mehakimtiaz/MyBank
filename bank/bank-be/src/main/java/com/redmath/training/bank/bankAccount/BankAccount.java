package com.redmath.training.bank.bankAccount;
import com.redmath.training.bank.transactions.Transactions;
import com.redmath.training.bank.user.User;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;


@CrossOrigin(origins = "http://localhost:8080")
@Entity
@Table(name="bank_account")
public class BankAccount {
    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AccountId;
    private String Title;
    private String AccountNumber;
    private String AccountType;
    private String currency;
    private String BankBranch;
    private Long UserId;
    private Integer currentBalance;
    private Integer availableBalance;



    public BankAccount(Long accountId, String title, String accountNumber, String accountType, String currency, String bankBranch, Long userId, Integer currentBalance, Integer availableBalance) {
        AccountId = accountId;
        Title = title;
        AccountNumber = accountNumber;
        AccountType = accountType;
        this.currency = currency;
        BankBranch = bankBranch;
        UserId = userId;
        this.currentBalance = currentBalance;
        this.availableBalance = availableBalance;
    }

    public BankAccount() {
    }

    public Integer getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Integer currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Integer getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Integer availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getUserId() {
        return UserId;
    }

    public String getBankBranch() {
        return BankBranch;
    }

    public void setBankBranch(String bankBranch) {
        BankBranch = bankBranch;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public Long getAccountId() {
        return AccountId;
    }

    public void setAccountId(Long accountId) {
        AccountId = accountId;
    }

}
