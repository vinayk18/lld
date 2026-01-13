package SplitWise;

import SplitWise.EqualSplitStrategy;
import SplitWise.SplitType;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Splitwise splitwise = new Splitwise();

        // ---- Create users ----
        User u1 = new User("U1", "Alice", "alice@mail.com");
        User u2 = new User("U2", "Bob", "bob@mail.com");
        User u3 = new User("U3", "Charlie", "charlie@mail.com");

        splitwise.addUser(u1);
        splitwise.addUser(u2);
        splitwise.addUser(u3);
        // ---- Create group ----
        Group tripGroup = new Group("G1", "Goa Trip");
        splitwise.createGroup(tripGroup);

        splitwise.addUserToGroup("U1", "G1");
        splitwise.addUserToGroup("U2", "G1");
        splitwise.addUserToGroup("U3", "G1");

        // ---- Add expense: Alice pays 300 for Alice, Bob, Charlie ----
        Expense expense1 = new Expense(
                "E1",
                u1,
                300,
                List.of(u1, u2, u3),
                SplitType.EQUAL,
                null,
                "Lunch"
        );

        splitwise.addExpense("G1", expense1, new EqualSplitStrategy());

        // ---- Print balances ----
        System.out.println("Balances after Expense 1:");

        printBalances("Alice", splitwise.getBalancesForUser("U1"));
        printBalances("Bob", splitwise.getBalancesForUser("U2"));
        printBalances("Charlie", splitwise.getBalancesForUser("U3"));

        /*
            Expected:
            Bob owes Alice 100
            Charlie owes Alice 100
         */

        // ---- Add another expense: Bob pays 150 for Bob and Charlie ----
        Expense expense2 = new Expense(
                "E2",
                u2,
                150,
                List.of(u2, u3),
                SplitType.EQUAL,
                null,
                "Taxi"
        );

        splitwise.addExpense("G1", expense2, new EqualSplitStrategy());

        System.out.println("\nBalances after Expense 2:");

        printBalances("Alice", splitwise.getBalancesForUser("U1"));
        printBalances("Bob", splitwise.getBalancesForUser("U2"));
        printBalances("Charlie", splitwise.getBalancesForUser("U3"));

        /*
            Expected net result:
            Bob owes Alice 100
            Charlie owes Alice 100
            Charlie owes Bob 75
         */
    }

    private static void printBalances(String userName, Map<User, Integer> balances) {
        System.out.println(userName + "'s balances:");

        if (balances.isEmpty()) {
            System.out.println("  No pending balances");
            return;
        }

        for (Map.Entry<User, Integer> entry : balances.entrySet()) {
            User other = entry.getKey();
            int amount = entry.getValue();

            if (amount > 0) {
                System.out.println("  Owes " + other.getName() + " : " + amount);
            } else {
                System.out.println("  Gets from " + other.getName() + " : " + (-amount));
            }
        }
    }
}