package LoggingFramework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerFactory {

    private static final Map<String, Logger> CACHE = new ConcurrentHashMap<>();
    private static LoggerConfig config;

    public static void init(LoggerConfig loggerConfig) {
        config = loggerConfig;
    }

    public static Logger getLogger(String name) {
        return CACHE.computeIfAbsent(name,
                n -> new Logger(n, config));
    }

    public static void shutdown() {
        CACHE.values().stream()
                .flatMap(l -> l.config.getLevelConfig(LogLevel.INFO)
                        .getAppenders().stream())
                .distinct()
                .forEach(Appender::close);
    }
}