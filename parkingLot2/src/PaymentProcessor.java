public class PaymentProcessor {

    private double balance;

    public PaymentProcessor(){
        this.balance = 100.0;
    }

    public void processPayment(double fare){
        balance += fare;
        System.out.println("processed fare: " + fare);
    }

}
