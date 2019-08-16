package com.redmath.training.bank.transactions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.redmath.training.bank.bankAccount.BankAccount;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long TransactionId;
    private double Amount;
    private  String TransactionType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date TransactionDate;
    private Long accountId;



    public Transactions(double amount, String transactionType, Date transactionDate,Long accountId) {
        Amount = amount;
        TransactionType = transactionType;
        TransactionDate = transactionDate;
        accountId=accountId;

    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Transactions() {
    }


    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getAmount() {
        return Amount;
    }

    public void setTransactionId(Long transactionID) {
        TransactionId = transactionID;
    }

    public Long getTransactionId() {
        return TransactionId;
    }

    public void setTransactionDate(Date transactionDate) {
        TransactionDate = transactionDate;
    }

    public Date getTransactionDate() {
        return TransactionDate;
    }
}
