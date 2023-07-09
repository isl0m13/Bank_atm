import java.security.MessageDigest;
import java.util.ArrayList;

public class User {
    private String firstName ; // User's first name
    private String lastName ; // User's last name
    private String uuid ; // User's ID
    private byte pinHash[]; // User's pin number
    private ArrayList<Account> accounts ; // Account list for this user

    /**
     * Create a new User
     * @param firstName the User's first name
     * @param lastName the user's last name
     * @param pin the User's account pin number
     * @param theBank the Bank object that the user is a customer of
     */


    public User (String firstName , String lastName , String pin , Bank theBank){

        //Set user's name
        this.firstName = firstName ;
        this.lastName = lastName ;

        //store the pin's  MD5 hash , rather than  the original value , for security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        } catch (Exception e) {
            System.out.println("error , caught exception:"+ e.getMessage());
            System.exit(1);
        }

        // get a new , unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList <Account> ();

        //print log message
        System.out.printf("New user %s, %s with ID %s created.\n",
                lastName, firstName, this.uuid);

    }



    public boolean validatePin(String apin){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(apin.getBytes()), this.pinHash);
        } catch (Exception e) {
            System.out.println("error , no such algorithm found");
            System.exit(1);
        }

        return false ;




    }

    String getUUID() {
        return this.uuid ;

    }

    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }

    public String getFirstName(){
        return this.firstName;
    }
    public void printAccountsSummary(){
        System.out.printf("\n\n%s's accounts summary\n", this.firstName);

        for (int a =0 ; a <this.accounts.size() ; a++){
            String line = this.accounts.get(a).getSummaryLine();
            System.out.println(line);
        }
    }
    public int numAccounts() {
        return this.accounts.size();
    }
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }

    public void printAcctTransHistory(int acc){
        this.accounts.get(acc).showTransactions();

    }

}
