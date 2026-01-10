package LoggingFramework;

public class Logger {
    private final String name;
    final LoggerConfig config;

    public Logger(String name, LoggerConfig config) {
        this.name = name;
        this.config = config;
    }

    public void debug( String msg ){
        log(LogLevel.DEBUG,msg);
    }

    public void info(String msg){
        log(LogLevel.INFO,msg);
    }

    public void warn(String msg)  { log(LogLevel.WARN, msg); }

    public void error(String msg) { log(LogLevel.ERROR, msg); }


    private void log(LogLevel level, String message) {
        if (!config.isEnabled(level)) return;

        LevelConfig lc = config.getLevelConfig(level);
        if (lc == null) return;

        LogEvent event = new LogEvent(level, name, message);
        String formatted = lc.getFormatter().format(event);

        for (Appender appender : lc.getAppenders()) {
            appender.append(formatted);
        }
    }
}
