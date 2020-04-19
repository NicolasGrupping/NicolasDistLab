package Server.Bank;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {
    private final AccountRepository repository;

    HttpController(AccountRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Accounts")
    List<Account> all() {
        return repository.findAll();
    }

    @PostMapping("/Accounts")
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    // Single item

    @GetMapping("/Accounts/{id}")
    Account one(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @PutMapping("/Accounts/{id}")
    Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {

        return repository.findById(id).map(Account -> {
                    Account.setName(newAccount.getName());
                    Account.setBalance(newAccount.getBalance());
                    return repository.save(Account);
                })
                .orElseGet(() -> {
                    newAccount.setId(id);
                    return repository.save(newAccount);
                });
    }

    @DeleteMapping("/Accounts/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
