package com.redmath.training.bank.transactions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8080")
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
    List<Transactions> findAll();
    Page<Transactions> findAll(Pageable pageable);
    @Query("select ba from Transactions ba where accountId=:id")
    Page<Transactions> findAllTransacctions(Long id,Pageable pageable);

 //   @Query("select ba from Transactions ba where TransactionId=:id")
    Optional<Transactions> findById(Long id);

    @Query("select ba from Transactions ba where accountId=:id")
    Optional<Transactions> findByAccountId(Long id);


}