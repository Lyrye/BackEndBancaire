package org.imt.nordeurope.nickler.backendbancaire.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account debtor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account creditor;

    @Column
    private Double value;

    @Column
    private Date date;


    public Transaction() {
    }
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Account getDebtor() {return debtor;}

    public void setDebtor(Account debtor) {
        this.debtor = debtor;}

    public Account getCreditor() {return creditor;}

    public void setCreditor(Account creditor) {
        this.creditor = creditor;}

    public Double getValue() {return value;}

    public void setValue(Double value) {this.value = value;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public Transaction( Account debtor, Account creditor, Double value, Date date) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.value = value;
        this.date = date;
    }

    //Currency of Transaction is always the same as Debtor
    public void doTransaction(){
        this.creditor.setBalance(this.creditor.getBalance()+value*(this.creditor.getCurrency().getCoefficient()));
        this.debtor.setBalance(this.debtor.getBalance()-value);
    }
}
