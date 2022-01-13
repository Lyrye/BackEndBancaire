package org.imt.nordeurope.nickler.backendbancaire.Model;


import org.imt.nordeurope.nickler.backendbancaire.Model.Enums.AccountType;
import org.imt.nordeurope.nickler.backendbancaire.Model.Enums.Currency;
import org.iban4j.*;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @Column
    private Long id;

    @Column
    private String ownerLastName;
    
    @Column
    private String ownerFirstName;

    @Column
    private String accountName;

    @Column
    private Double Balance;

    @Column
    private AccountType accountType;

    @Column
    private Currency currency;

    @Column
    private String IBAN;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getOwnerLastName() {return ownerLastName;}

    public void setOwnerLastName(String ownerLastName) {this.ownerLastName = ownerLastName;}

    public String getAccountName() {return accountName;}

    public void setAccountName(String accountName) {this.accountName = accountName;}

    public Double getBalance() {return Balance;}

    public void setBalance(Double balance) {Balance = balance;}

    public AccountType getAccountType() {return accountType;}

    public void setAccountType(AccountType accountType) {this.accountType = accountType;}

    public String getOwnerFirstName() {return ownerFirstName;}

    public void setOwnerFirstName(String ownerFirstName) {this.ownerFirstName = ownerFirstName;}

    public void setIBAN (Iban iban){
        this.IBAN = iban.toFormattedString();
    }
    public Iban getIban(){
        return Iban.valueOf(IBAN);
    }
}
