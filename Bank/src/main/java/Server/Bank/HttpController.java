package Server.Bank;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

@RestController
public class HttpController {
    private final AccountRepository repository;
    private final AccountResourceAssembler assembler;


    HttpController(AccountRepository repository, AccountResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/Accounts")
    CollectionModel<EntityModel<Account>> all() {

        List<EntityModel<Account>> Accounts = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(Accounts,
                linkTo(methodOn(HttpController.class).all()).withSelfRel());
    }

    @PostMapping("/newAccount")
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    // Single item

    @GetMapping("/getAccount/{id}")
    EntityModel<Account> one(@PathVariable Long id) {

        Account account = repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

        return assembler.toModel(account);
    }

    @PutMapping("/changeAccount/{id}")
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

    @DeleteMapping("/deleteAccount/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
