package org.imt.nordeurope.nickler.backendbancaire.Controller;

import com.google.gson.Gson;
import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Model.IBANValidation;
import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.AccountRepository;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.TransactionRepository;
import org.imt.nordeurope.nickler.backendbancaire.Service.IIbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class BankController {

    //todo add service intermediare pour ne pas passer direct par les repository !
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Inject
    IIbanService ibanService;


    @GetMapping(value = {"/account"},produces = "application/json")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/transactions"},produces = "application/json")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/account/{Account_IBAN}/transactions"},produces = "application/json")
    public ResponseEntity<List<Transaction>> getTransactionsFromAccount(@PathVariable String Account_IBAN) {
        Account account= accountRepository.getByIban(Account_IBAN);
        return new ResponseEntity<>(transactionRepository.findTransactionsByCreditorOrDebtor(account,account), HttpStatus.OK);
    }

    @PostMapping(value = "/account",consumes ="application/json")
    public ResponseEntity<Void> createAccount(@RequestBody String accountInJson) {
        Gson gson = new Gson();
        Account account = new Account() ;
        account = gson.fromJson(accountInJson,Account.class) ;
        IBANValidation ibanValidation = ibanService.checkIBAN((account.getIban()));
        if(ibanValidation.getValid()){
            accountRepository.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @DeleteMapping(value = {"/account/{Account_IBAN}"})
    public ResponseEntity<Void> deleteAccount(@PathVariable String Account_IBAN) {

        Account account= accountRepository.getByIban(Account_IBAN);
        List<Transaction> list = transactionRepository.findTransactionsByCreditorOrDebtor(account,account);
        for (Transaction transaction:list)
        {
            transactionRepository.delete(transaction);
        }
       accountRepository.delete(account);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
