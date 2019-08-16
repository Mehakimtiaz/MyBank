package com.redmath.training.bank.bankAccount;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/account")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/welcome")
    public ResponseEntity<Map<String, ?>> welcome(Authentication authentication) {
        Map<String, Map<String, Map<String, Object>>> result = new HashMap<>();
        result.put("Welcome", new HashMap<String, Map<String, Object>>());
        result.get("Welcome").put("@Value", new HashMap<String, Object>());
        result.get("Welcome").put("Security", new HashMap<String, Object>());
        result.get("Welcome").get("Security").put("authentication", authentication);
        return ResponseEntity.ok(result);
    }
   @GetMapping("/{account_id}")
    public ResponseEntity<BankAccount> get(@PathVariable("account_id") Long id) {
       Optional<BankAccount> Bank = bankAccountService.get(id);
        return Bank.isPresent() ? ResponseEntity.ok(Bank.get()): ResponseEntity.notFound().build();
    }


    @GetMapping("/title/{title}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<BankAccount> get(@PathVariable(value="title") String title) {
        Optional<BankAccount> Bank = bankAccountService.get(title);
        return Bank.isPresent() ? ResponseEntity.ok(Bank.get()): ResponseEntity.notFound().build();


    }
    @GetMapping
    public List<BankAccount> getAll() {
       return bankAccountService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<BankAccount> create(@RequestBody BankAccount ba, Authentication auth) {
        ba.setTitle(auth.getName());
        return ResponseEntity.ok(bankAccountService.create(ba));
    }

    @PutMapping("/{account_id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or @bankAccountService.updateAuthorised(authentication, #account_id)")
    public ResponseEntity<BankAccount> update(@PathVariable("account_id") Long id,@RequestBody BankAccount update) {
        Optional<BankAccount> bAccount = bankAccountService.update(id, update);
        return bAccount.isPresent() ? ResponseEntity.ok(bAccount.get()): ResponseEntity.notFound().build() ;
    }
    @DeleteMapping("/{account_id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("account_id") Long id) {
        bankAccountService.delete(id);
        return ResponseEntity.ok().build();
    }


}
