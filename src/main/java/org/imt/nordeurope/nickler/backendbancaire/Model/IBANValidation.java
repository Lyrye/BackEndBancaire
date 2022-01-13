package org.imt.nordeurope.nickler.backendbancaire.Model;

public class IBANValidation {

    private String IBAN;
    private Boolean isValid;

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
