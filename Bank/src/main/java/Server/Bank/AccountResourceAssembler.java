package Server.Bank;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountResourceAssembler  implements RepresentationModelAssembler<Account, EntityModel<Account>>{

    @Override
    public EntityModel<Account> toModel(Account account) {
        return new EntityModel<>(account,
            linkTo(methodOn(HttpController.class).one(account.getId())).withSelfRel(),
            linkTo(methodOn(HttpController.class).all()).withRel("Accounts"));
    }
}

