package SplitWise;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ledger {
    Map<User, Map<User,Integer>> balances = new HashMap<>();

    public void applyDeltas(List<BalanceDelta> deltas) {
        for (BalanceDelta delta : deltas) {
            applyDelta(delta);
        }
    }

    private void applyDelta( BalanceDelta delta ){
        User from = delta.getFrom();
        User to = delta.getTo();
        int amount = delta.getAmount();

        int existing = getBalance(from,to);
        if( existing > 0 ){
            setBalance(from,to,existing+amount);
            return;
        }

        int reverse = getBalance(to,from);
        if( reverse > 0){
            if( reverse > amount ){
                setBalance(from,to,reverse-amount);
            } else if ( reverse < amount ){
                setBalance(to,from,reverse-amount);
            } else {
                removeBalance(to,from);
            }
            return;
        }
        setBalance(from,to,amount);
    }

    public Map<User, Integer> getBalanceForUser(User user) {
        Map<User, Integer> result = new HashMap<>();

        Map<User, Integer> outgoing = balances.getOrDefault(user, Collections.emptyMap());
        outgoing.forEach(result::put);

        for (Map.Entry<User, Map<User, Integer>> entry : balances.entrySet()) {
            User other = entry.getKey();
            if (entry.getValue().containsKey(user)) {
                result.put(other, -entry.getValue().get(user));
            }
        }

        return result;
    }


    private int getBalance(User from ,User to){
        return balances
            .getOrDefault(from, Collections.emptyMap())
            .getOrDefault(to,0);
    }

    private void setBalance(User from ,User to, int amount){
        balances
            .computeIfAbsent( from, k -> new HashMap<>())
            .put(to,amount);
    }

    private void removeBalance( User from, User to ){
        Map<User, Integer> map = balances.get(from);
        if( map != null ){
            map.remove(to);
            if( map.isEmpty() ){
                map.remove(from);
            }
        }
    }

}
