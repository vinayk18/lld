import java.util.ArrayList;
import java.util.List;

public class Logger {
        private String name;
        private LogLevel currentLevel;
        private LogFormatter formatter;
        private final List<Appender> appenders;
        private LogHandler firstHandler;

    public Logger( String name , LogLevel currentLevel ){
            this.name = name;
            this.currentLevel = currentLevel;
            this.formatter = new SimpleFormatter();
            this.appenders = new ArrayList<>();
            LogHandler debug = new DebugHandler();
            LogHandler info = new InfoHandler();
            LogHandler error = new ErrorHandler();
            debug.setNext(info);
            info.setNext(error);
            this.firstHandler = debug;
        }

        public void addAppender( Appender appender ){
            appenders.add(appender);
        }

        public void setFormatter( LogFormatter formatter ){
            this.formatter = formatter;
        }

        public void log( LogLevel level , String message ){
            if (level.getPriority() < currentLevel.getPriority()) return;

            String enriched = "[" + name + "] " + message;
            firstHandler.handle(level, enriched);
        }

        public void setLevel(LogLevel level) {
            this.currentLevel = level;
        }

        private boolean shouldLog( LogLevel level ){
            int msgLevel = getLevelPriority(level);
            int current = getLevelPriority(this.currentLevel);
            return msgLevel >= current;
        }

        private int getLevelPriority(LogLevel level) {
            switch (level) {
                case DEBUG: return 1;
                case INFO: return 2;
                case ERROR: return 3;
                default: return 0;
            }
        }

        public void debug(String msg){
            log( LogLevel.DEBUG, msg );
        }

        public void info(String msg){
            log( LogLevel.INFO, msg );
        }

        public void error(String msg){
            log( LogLevel.ERROR, msg );
        }

}
