package org.imt.nordeurope.nickler.backendbancaire.Repositories;

import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    public Account getByIBAN(String IBAN);

}
