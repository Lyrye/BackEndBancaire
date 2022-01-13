package org.imt.nordeurope.nickler.backendbancaire.Repositories;

import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
