import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {

    private static final Map<String,Logger> loggers = new ConcurrentHashMap<>();
    private static final LogManager instannce = new LogManager();
    private final List<Logger> observers = new ArrayList<>();
    private volatile LogLevel globalLevel = LogLevel.INFO;

    private LogManager(){}

    public static LogManager getInstance(){
        return  instannce;
    }

    public Logger getLogger(String name){
        return loggers.computeIfAbsent( name , n-> {
           Logger logger = new Logger(n,globalLevel);
           register(logger);
           return logger;
        });
    }

    public void register(Logger logger){
        synchronized ( observers ){
            observers.add(logger);
        }
    }

    public void updateGlobalLevel(LogLevel newLevel){
        this.globalLevel = newLevel;
        synchronized ( observers ){
            for( Logger logger : observers ){
                logger.setLevel(newLevel);
            }
        }
    }

}
