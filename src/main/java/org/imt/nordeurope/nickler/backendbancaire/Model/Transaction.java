package org.imt.nordeurope.nickler.backendbancaire.Model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Transaction {
    @Id
    @Column
    private Long id;


    @ManyToOne
    private Account Debtor;

    @ManyToOne
    private Account Creditor;

    @Column
    private Double value;

    @Column
    private Date date;


    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Account getDebtor() {return Debtor;}

    public void setDebtor(Account debtor) {Debtor = debtor;}

    public Account getCreditor() {return Creditor;}

    public void setCreditor(Account creditor) {Creditor = creditor;}

    public Double getValue() {return value;}

    public void setValue(Double value) {this.value = value;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}
}
