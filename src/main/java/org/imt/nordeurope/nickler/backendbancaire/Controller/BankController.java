package org.imt.nordeurope.nickler.backendbancaire.Controller;

import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.AccountRepository;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping(value = {"/accounts"},produces = "application/json")
    public ResponseEntity<List<Account>> getAllAccounts(Model model) {

        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }
}
