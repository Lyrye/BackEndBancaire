package org.imt.nordeurope.nickler.backendbancaire.Repositories;

import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    public List<Transaction> findTransactionsByCreditorOrDebtor(Account Creditor, Account Debtor);
}
