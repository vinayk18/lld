package SplitWise;

import java.util.*;

public class Splitwise {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Group> groups = new HashMap<>();
    private final Ledger ledger = new Ledger();

    // ---------- User operations ----------

    public void addUser(User user) {
        if (users.containsKey(user.getId())) {
            throw new IllegalArgumentException("User already exists: " + user.getId());
        }
        users.put(user.getId(), user);
    }

    public User getUser(String userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        return user;
    }

    // ---------- Group operations ----------

    public void createGroup(Group group) {
        if (groups.containsKey(group.getId())) {
            throw new IllegalArgumentException("Group already exists: " + group.getId());
        }
        groups.put(group.getId(), group);
    }

    public Group getGroup(String groupId) {
        Group group = groups.get(groupId);
        if (group == null) {
            throw new IllegalArgumentException("Group not found: " + groupId);
        }
        return group;
    }

    public void addUserToGroup(String userId, String groupId) {
        User user = getUser(userId);
        Group group = getGroup(groupId);
        group.addUser(user);
    }

    // ---------- Expense orchestration ----------

    public void addExpense(
            String groupId,
            Expense expense,
            SplitStrategy splitStrategy
    ) {
        Group group = getGroup(groupId);

        // Validate group membership
        for (User participant : expense.getParticipants()) {
            if (!group.hasUser(participant)) {
                throw new IllegalArgumentException(
                        "User " + participant.getId() + " not part of group " + groupId
                );
            }
        }

        // 1. Apply split logic
        List<BalanceDelta> deltas = splitStrategy.split(expense);

        // 2. Update ledger
        ledger.applyDeltas(deltas);

        // 3. Record expense in group
        group.addExpense(expense);
    }

    // ---------- Queries ----------

    public Map<User, Integer> getBalancesForUser(String userId) {
        User user = getUser(userId);
        return ledger.getBalanceForUser(user);
    }
}