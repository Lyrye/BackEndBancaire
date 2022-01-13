package org.imt.nordeurope.nickler.backendbancaire.Model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account debtor;

    @ManyToOne
    private Account creditor;

    @Column
    private Double value;

    @Column
    private Date date;


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
}
