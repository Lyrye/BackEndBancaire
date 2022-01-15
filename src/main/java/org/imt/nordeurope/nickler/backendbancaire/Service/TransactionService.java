package org.imt.nordeurope.nickler.backendbancaire.Service;

import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionService implements ITransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByCreditorOrDebtor(Account account) {
        return transactionRepository.findTransactionsByCreditorOrDebtor(account,account);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    @Override
    public void deleteTransactions(List<Transaction> transactions) {
        transactionRepository.deleteAll(transactions);
    }
}
