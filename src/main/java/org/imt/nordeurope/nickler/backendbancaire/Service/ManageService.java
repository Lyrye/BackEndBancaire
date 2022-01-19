package org.imt.nordeurope.nickler.backendbancaire.Service;

import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageService implements IManageService{
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    IIbanService ibanService;

    @Override
    public void doTransaction(Transaction transaction) {
        if( (ibanService.checkIBAN(transaction.getCreditor().getIban())).getValid() && (ibanService.checkIBAN(transaction.getDebtor().getIban())).getValid()){
            //mis a jour des comptes contenu dans la transaction pour obtenir la bonne valeur de balance
            transaction.doTransaction();
            accountService.saveAccount(transaction.getDebtor());
            accountService.saveAccount(transaction.getCreditor());
        }
    }
}
