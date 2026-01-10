package LoggingFramework;

import java.util.Map;

public class LoggerConfig {

    private final LogLevel minLevel;
    private final Map<LogLevel, LevelConfig> levelConfigs;

    public LoggerConfig(LogLevel minLevel,
                        Map<LogLevel, LevelConfig> levelConfigs) {
        this.minLevel = minLevel;
        this.levelConfigs = levelConfigs;
    }

    public boolean isEnabled(LogLevel level) {
        return level.isGreaterOrEqual(minLevel);
    }

    public LevelConfig getLevelConfig(LogLevel level) {
        return levelConfigs.get(level);
    }
}