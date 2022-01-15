package org.imt.nordeurope.nickler.backendbancaire.Model;


import org.imt.nordeurope.nickler.backendbancaire.Model.Enums.AccountType;
import org.imt.nordeurope.nickler.backendbancaire.Model.Enums.Currency;
import org.iban4j.*;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String ownerLastName;
    
    @Column
    private String ownerFirstName;

    @Column
    private String accountName;

    @Column
    private Double balance;

    @Column
    private AccountType accountType;

    @Column
    private Currency currency;

    @Column
    private String iban;

    public Account(){}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getOwnerLastName() {return ownerLastName;}

    public void setOwnerLastName(String ownerLastName) {this.ownerLastName = ownerLastName;}

    public String getAccountName() {return accountName;}

    public void setAccountName(String accountName) {this.accountName = accountName;}

    public Double getBalance() {return balance;}

    public void setBalance(Double balance) {
        this.balance = balance;}

    public AccountType getAccountType() {return accountType;}

    public void setAccountType(AccountType accountType) {this.accountType = accountType;}

    public String getOwnerFirstName() {return ownerFirstName;}

    public void setOwnerFirstName(String ownerFirstName) {this.ownerFirstName = ownerFirstName;}

    public void setIban(Iban iban){
        this.iban = iban.toFormattedString();
    }

   public Iban IbanObject(){
        return Iban.valueOf(iban);
    }
    public String getIban(){
        return iban;
    }

}
