package com.redmath.training.bank.transactions;

import com.redmath.training.bank.bankAccount.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8080")
@Service
public class TransactionsService {
   // private final TransactionsRepository TransactionsRespository;
    private final TransactionsRepository TransactionsRepository;




    public TransactionsService( TransactionsRepository TransactionsRespository) {

        this.TransactionsRepository = TransactionsRespository;
    }
    public List<Transactions> getAll() {
        return TransactionsRepository.findAll();
    }
    public Page<Transactions> findAll(Pageable pageable)
    {
        return  TransactionsRepository.findAll(pageable);

    }
    public Page<Transactions> findAllTransactions(Long id,Pageable pageable)
    {
        return  TransactionsRepository.findAllTransacctions(id,pageable);

    }
    public Optional<Transactions> get(Long id) {
        return TransactionsRepository. findById(id);
    }
    public Optional<Transactions> getByAccountId(Long id) {
        return TransactionsRepository.findByAccountId(id);
    }
    public Transactions create(Transactions ba) {
        return TransactionsRepository.save(ba);
    }
}