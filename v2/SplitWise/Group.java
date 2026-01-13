package SplitWise;

import java.util.*;

public class Group {
   private final String id;
   private final String name;
   private final Set<User> users;
   private final List<Expense> expenses;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        this.users = new HashSet<>();
        this.expenses = new ArrayList<>();
    }

    public void addUser( User user){
        users.add(user);
    }

    public void removeUser( User user){
        users.remove(user);
    }

    public boolean hasUser(User user){
        return users.contains(user);
    }

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    public void addExpense( Expense expense ){
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
