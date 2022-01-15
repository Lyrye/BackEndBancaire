package org.imt.nordeurope.nickler.backendbancaire.Service;

import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class AccountService implements IAccountService{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ITransactionService transactionService;


    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getByIban(String iban) {
        return accountRepository.getByIban(iban);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Account account) {
        transactionService.deleteTransactions(transactionService.getTransactionsByCreditorOrDebtor(account));
        accountRepository.delete(account);
    }
}
