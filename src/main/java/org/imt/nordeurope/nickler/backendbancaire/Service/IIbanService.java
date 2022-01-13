package org.imt.nordeurope.nickler.backendbancaire.Service;

import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Model.IBANValidation;
import org.springframework.web.bind.annotation.RequestParam;

public interface IIbanService {

    public IBANValidation checkIBAN(@RequestParam String IBAN);

}
