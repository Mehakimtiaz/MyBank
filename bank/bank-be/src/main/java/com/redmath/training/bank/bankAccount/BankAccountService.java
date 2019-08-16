package com.redmath.training.bank.bankAccount;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankaccountRepository;

    public BankAccountService(BankAccountRepository bankaccoutnRespository) {
        this.bankaccountRepository = bankaccoutnRespository;
    }
    public List<BankAccount> getAll() {
        return bankaccountRepository.findAll();
    }
    public Optional<BankAccount> get(Long id) {
        return bankaccountRepository.findById(id);
    }

    public Optional<BankAccount> get(String title) {
        return bankaccountRepository.findByTitle(title);
    }
    public BankAccount create(BankAccount ba) {
        return bankaccountRepository.save(ba);
    }

    public Optional<BankAccount> update(Long id, BankAccount update) {
        Optional<BankAccount> b = bankaccountRepository.findById(id);
        if (b.isPresent()) {
            b.get().setTitle(update.getTitle());
            b.get().setAccountNumber(update.getAccountNumber());
            b.get().setAccountType(update.getAccountType());
            b.get().setBankBranch(update.getBankBranch());
            b.get().setCurrency(update.getCurrency());
           bankaccountRepository.save(b.get());
        }
        return b;
    }
    public void delete(Long id) {
        bankaccountRepository.deleteById(id);
    }

    public boolean updateAuthorised(Authentication auth, Long id) {
        Optional<BankAccount> news = bankaccountRepository.findById(id);
        if (!(news.isPresent())) {
            throw new IllegalStateException("News not found");
        }
        return auth.getName().equals(news.get().getTitle());
    }
}
