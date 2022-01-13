package org.imt.nordeurope.nickler.backendbancaire.Service;

import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Model.IBANValidation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Component
public class IbanService implements IIbanService {

    static final String URL_BACKEND = "http://localhost:9000/";

    @Override
    public IBANValidation checkIBAN(@RequestParam String IBAN) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL_BACKEND + "checkIBAN?IBAN=" + IBAN , IBANValidation.class);
    }

}
