package SplitWise;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<BalanceDelta> split(Expense expense) {

        List<BalanceDelta> deltas = new ArrayList<>();

        int total = expense.getTotalAmount();
        List<User> participants = expense.getParticipants();
        User payer = expense.getPayer();

        int n = participants.size();
        int share = total / n;

        for (User user : participants) {
            if (!user.equals(payer)) {
                deltas.add(
                        new BalanceDelta(user, payer, share)
                );
            }
        }

        return deltas;
    }
}