package com.redmath.training.bank.bankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository   extends JpaRepository<BankAccount ,Long> {


    List<BankAccount> findAll();

   // @Query("SELECT p FROM BankAccount p Join User u WHERE p.Title =u.userName")
    Optional<BankAccount> findById(Long id);


    @Query("select ba from BankAccount ba where title like %?1%")
   Optional<BankAccount> findByTitle(String title);
}
