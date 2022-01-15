package org.imt.nordeurope.nickler.backendbancaire.Controller;

import com.google.gson.Gson;
import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Model.IBANValidation;
import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.imt.nordeurope.nickler.backendbancaire.Service.IAccountService;
import org.imt.nordeurope.nickler.backendbancaire.Service.IIbanService;
import org.imt.nordeurope.nickler.backendbancaire.Service.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class BankController {

    @Inject
    IIbanService ibanService;

    @Inject
    IAccountService accountService;

    @Inject
    ITransactionService transactionService;


    @GetMapping(value = {"/account"},produces = "application/json")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/transactions"},produces = "application/json")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping(value = {"/account/{Account_IBAN}/transactions"},produces = "application/json")
    public ResponseEntity<List<Transaction>> getTransactionsFromAccount(@PathVariable String Account_IBAN) {
        Account account= accountService.getByIban(Account_IBAN);
        return new ResponseEntity<>(transactionService.getTransactionsByCreditorOrDebtor(account), HttpStatus.OK);
    }

    @PostMapping(value = "/account",consumes ="application/json")
    public ResponseEntity<Void> createAccount(@RequestBody String accountInJson) {
        Gson gson = new Gson();
        Account account = new Account() ;
        account = gson.fromJson(accountInJson,Account.class) ;

        if (accountService.saveAccount(account)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @DeleteMapping(value = {"/account/{Account_IBAN}"})
    public ResponseEntity<Void> deleteAccount(@PathVariable String Account_IBAN) {
        Account account= accountService.getByIban(Account_IBAN);
        accountService.deleteAccount(account);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
