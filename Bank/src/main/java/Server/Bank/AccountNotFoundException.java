package Server.Bank;

public class AccountNotFoundException extends RuntimeException{
    AccountNotFoundException(Long id) {
        super("Could not find account " + id);
    }
}
