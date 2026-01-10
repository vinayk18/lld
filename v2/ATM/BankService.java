package ATM;

public class BankService {
    private Account userAccount;

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    boolean checkIsValidCard(Card card ){
        return true;
    }

    boolean checkIsValidPIN( Card card , int PIN){
        return true;
    }

    void debit(Account account, int amount ){
    }

    void credit(Account account, int amount){

    }

    boolean hasSufficientBalance( Account account, int amount ){
        return  true;
    }

    int getBalance(Account account){
        return 1000;
    }
}
