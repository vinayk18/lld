import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static int nextUserId = 1;
    private Map<Integer, User> userMap = new HashMap<>();

    public void addUser(String name){
        User user = new User(name,nextUserId++);
        userMap.put( user.getId(), user );
    }

    public void removeUser( User user ){
        int id = user.getId();
        userMap.remove(id);
    }

    public User getUserById( Integer id ){
        return userMap.get( id );
    }
}
