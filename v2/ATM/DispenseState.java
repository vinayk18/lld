package ATM;

public class DispenseState implements State {

    public DispenseState(ATM atm) {
        dispense(atm);
    }

    private void dispense(ATM atm) {
        try {
            TransactionType txn = atm.getSelectedTxn();
            if (txn == TransactionType.WITHDRAW) {
                handleWithdraw(atm);
            } else if (txn == TransactionType.BALANCE_INQUIRY) {
                handleBalanceInquiry(atm);
            }
        } catch (Exception e) {
             atm.reset();
            throw e;
        }
         atm.reset();
    }

    private void handleWithdraw(ATM atm) {
        int amount = atm.getWithdrawAmount();
        Account account = atm.getAccount();
        BankService bank = atm.getBankService();
        CashDispenser dispenser = atm.getCashDispenser();

         if (!bank.hasSufficientBalance(account, amount)) {
            throw new IllegalStateException("Insufficient account balance");
        }

         if (!dispenser.canDispense(amount)) {
            throw new IllegalStateException("ATM has insufficient cash");
        }

         bank.debit(account, amount);

        try {
             dispenser.dispense(amount);
        } catch (Exception e) {
             bank.credit(account, amount);
            throw new RuntimeException("Cash dispense failed. Amount rolled back.");
        }
    }

    private void handleBalanceInquiry(ATM atm) {
        int balance = atm.getBankService().getBalance(atm.getAccount());
        System.out.println("Current balance: " + balance);
    }

    @Override
    public void readCard(Card card, ATM atm) {
        throw new IllegalStateException("Dispensing in progress");
    }

    @Override
    public void readPIN(int PIN, ATM atm) {
        throw new IllegalStateException("Dispensing in progress");
    }

    @Override
    public void selectTransaction(TransactionType txn, int amount, ATM atm) {
        throw new IllegalStateException("Dispensing in progress");
    }

    @Override
    public void cancel(ATM atm) {
        throw new IllegalStateException("Cannot cancel during dispensing");
    }
}