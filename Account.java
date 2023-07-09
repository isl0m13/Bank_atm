import java.util.Scanner;
import java.util.ArrayList;
public class Account {
    private String name ; // Name of account
    private String uuid ; // Account ID number
    private User holder ; // Object thatowns this account
    private ArrayList<Transaction> transactions ; // list for ttransactions for this account

    /**
     * Create a new Account
     * @param name the name of the account
     * @param holder the User object that holds this account
     * @param theBank the bank that issues the account
     */

    public Account (String name , User holder, Bank theBank){
        //set the account name and holder
        this.name = name ;
        this.holder = holder ;

        //get new account UUID
        this.uuid = theBank.getNewAccountUUID ();

        //init transaction
        this.transactions = new ArrayList<Transaction> ();


    }

    /**
     * Get account ID
     * @return the uuid
     */
    public String getUUID() {
        return uuid;
    }

    public String  getSummaryLine(){
        double balance = this.getBalance();

        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance,
                    this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance,
                    this.name);
        }


    }

    public double getBalance(){
        double balance = 0;
        for (Transaction t :this.transactions){
            balance += t.getMoney();
        }
        return balance ;
    }
    public void addTransaction(double amount, String memo) {

        // create new transaction and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);

    }

    public void showTransactions(){
        for (Transaction t : transactions){
            t.transactionInfo();
        }
    }

}