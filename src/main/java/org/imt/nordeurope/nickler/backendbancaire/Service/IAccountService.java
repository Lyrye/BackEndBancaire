package org.imt.nordeurope.nickler.backendbancaire.Service;

import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

public interface IAccountService {

   public List<Account> getAll();
   public Account getByIban(String iban);
   public Boolean saveAccount(Account account);
   public void deleteAccount(Account account);
}
