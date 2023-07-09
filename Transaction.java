/**
 * Created by isl0m13*/

import java.util.Date;
public class Transaction {
    private double amount ;// Amount of this transaction
    private Date timestamp ; // Time and date of this transaction
    private String memo ; // A memo for this transaction
    private Account inAccount ; //Account in wich , transaction was performed

    /**
     * Create the transaction
     * @param amount the amoun transacted
     * @param inAccount the account the transaction belong to
     */
    public Transaction(double amount , Account inAccount){
        this.amount = amount ;
        this.inAccount = inAccount ;
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     * Create the transaction
     * @param amount the amoun transacted
     * @param memo the memo for the transaction
     * @param inAccount the account the transaction belong to
     */
    public Transaction(double amount, String memo, Account inAccount) {

        this(amount, inAccount); //calls two-arg constructor first
        this.memo = memo; //set the demo

    }

    public double getMoney(){
        return this.amount;
    }
    public void transactionInfo(){
        System.out.println(this.amount + " " +this.inAccount +  " " + this.memo + " "+ this.timestamp);
    }

}
