import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name ;
    private ArrayList<User> users ;
    private ArrayList<Account > accounts ;

    /**
     * Create a new Bank object with empty lists of users and accounts
     * @param name the name of the bank
     */
    public Bank(String name ){
        this.name = name ;
        accounts = new ArrayList<Account>();
        users = new ArrayList<User> ();
    }


    public void addAccount(Account acc){
        this.accounts.add(acc);
    }

    /**
     * Generate a new unversally unique ID for a user
     * @return the uuid
     */
    public String getNewUserUUID(){
        Random rng = new Random();
        boolean nonUnique  ;
        String uuid  ;
        int len = 6 ;

        //continue looping until we get a unique ID
        do {
            // generate the number
            uuid = "" ;
            for (int c = 0 ; c<len ; c++){
                uuid +=( (Integer)rng.nextInt(10)).toString() ;

            }
            //check to make sure it's unique
            nonUnique = false ;
            for (User u : this.users){
                if (uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true ;
                    break ;
                }
            }

        }while (nonUnique);
        return uuid ;
    }
    public String getNewAccountUUID(){
        Random rng = new Random();
        boolean nonUnique  ;
        String uuid  ;
        int len = 10 ;


        //continue looping until we get a unique ID
        do {
            // generate the number
            uuid = "" ;
            for (int c = 0 ; c<len ; c++){
                uuid +=( (Integer)rng.nextInt(10)).toString() ;

            }
            //check to make sure it's unique
            nonUnique = false ;
            for (Account a : this.accounts){
                if (uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true ;
                    break ;
                }
            }

        }while (nonUnique);
        return uuid ;

    }

    /**
     * Create a new user of bank
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's pin
     * @return    the new User object
     */
    public User addUser(String firstName , String lastName , String pin){
        //create a new User object and add it to our list
        User newUser = new User ( firstName ,  lastName ,  pin , this);
        this.users.add(newUser);

        //create saving account for user and add to User and Bank accpunt lists
        Account newAccount = new Account("savings" , newUser , this);
        this.accounts.add(newAccount);
        newUser.addAccount(newAccount);
        return newUser;



    }

    /**
     * Get the User object associated with a particular userID and pin , if they are valid
     * @param userID  the UUID of the user to log in
     * @param pin     the pin of the user
     * @return        the User object , of the login is succesful , or null , if it is not
     */
    public User userLogin (String userID , String pin){
        // search through list of users
        for (User u :this.users){
            //check user ID is correct
            if (u.getUUID().compareTo(userID) ==0 &&u.validatePin(pin) )
                return u ;



        }

        return null ; //if we haven't found the user have an incorrect pin
    }
    public String getName(){
        return this.name;
    }
}
