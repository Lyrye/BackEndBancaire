package org.imt.nordeurope.nickler.backendbancaire.Service;

import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.springframework.transaction.TransactionSuspensionNotSupportedException;


import java.util.List;

public interface ITransactionService {

     List<Transaction> getAllTransactions();
     List<Transaction> getTransactionsByCreditorOrDebtor(Account account);
     void saveTransaction(Transaction transaction);
     void deleteTransaction(Transaction transaction);
     void deleteTransactions(List<Transaction> transactions);
     void doTransaction(Transaction transaction);
}
