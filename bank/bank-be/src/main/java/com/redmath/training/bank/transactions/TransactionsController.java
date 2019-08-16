package com.redmath.training.bank.transactions;
import com.redmath.training.bank.bankAccount.BankAccount;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private final TransactionsService TransactionsService;
    public TransactionsController(TransactionsService transactionsService) {
        this.TransactionsService = transactionsService;
    }
    @GetMapping("/amount/{pageNumber}/{pageSize}")
    public Page<Transactions> getAllByAmount(@PathVariable("pageNumber") Integer pageNo, @PathVariable("pageSize") Integer pageSize)
    {
         return   TransactionsService.findAll((Pageable) PageRequest.of(pageNo, pageSize ));
    }
    @GetMapping("/{account_Id}/{pageNumber}/{pageSize}")
    public Page<Transactions> getAllByAmount(@PathVariable("account_Id") Long id,@PathVariable("pageNumber") Integer pageNo, @PathVariable("pageSize") Integer pageSize)
    {
        return   TransactionsService.findAllTransactions(id,(Pageable) PageRequest.of(pageNo, pageSize ));
    }
    @GetMapping
    public List<Transactions> getAll() {
        return TransactionsService.getAll();
    }
    @GetMapping("/{transaction_id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<Transactions> get(@PathVariable("transaction_id") Long id) {
        Optional<Transactions> Bank = TransactionsService.get(id);
        return Bank.isPresent() ? ResponseEntity.ok(Bank.get()): ResponseEntity.notFound().build();
    }
    @GetMapping("byAccountId/{account_id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<Transactions> getByAccountId(@PathVariable("account_id") Long id) {
            Optional<Transactions> Bank = TransactionsService.getByAccountId(id);
        return Bank.isPresent() ? ResponseEntity.ok(Bank.get()): ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Transactions> create(@RequestBody Transactions ba, Authentication auth) {

        return ResponseEntity.ok(TransactionsService.create(ba));
    }
}
