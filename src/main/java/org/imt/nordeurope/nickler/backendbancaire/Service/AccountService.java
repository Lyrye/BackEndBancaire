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

    @Autowired
    IIbanService ibanService;


    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getByIban(String iban) {
        return accountRepository.getByIban(iban);
    }

    @Override
    public Boolean saveAccount(Account account) {
        boolean iscreated = false;
        if((ibanService.checkIBAN(account.getIban()).getValid())){
            try {
                accountRepository.save(account);
            } catch (Throwable t){
                t.printStackTrace();
            }
            iscreated= true;
        }
        return iscreated;
    }

    @Override
    public void deleteAccount(Account account) {
        transactionService.deleteTransactions(transactionService.getTransactionsByCreditorOrDebtor(account));
        accountRepository.delete(account);
    }
}
