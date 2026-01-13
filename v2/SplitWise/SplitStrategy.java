package SplitWise;

import java.util.List;

public interface SplitStrategy {
    List<BalanceDelta> split(Expense expense);
}
