public class BankClient {

    public boolean authenticate(ATMCard card, int pin){
        return true;
    }

    public boolean withdrawalValidation(ATMCard card,int amount){
        return true;
    }

    public int checkBalance(ATMCard card ){
        return 0;
    }

    public boolean transfer( ATMCard card , int amount, int account2){
        return true;
    }
}
