public class ATMCard {

    private int cardNumber;
    private String cardHolder;

    ATMCard(int cardNumber, String cardHolder){
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }
}
