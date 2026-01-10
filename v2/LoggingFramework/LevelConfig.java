package LoggingFramework;

import java.util.List;

public class LevelConfig {
    private final LogFormatter formatter;
    private final List<Appender> appenderList;

    public LevelConfig(LogFormatter formatter, List<Appender> appenderList) {
        this.formatter = formatter;
        this.appenderList = appenderList;
    }

    public List<Appender> getAppenders() {
        return appenderList;
    }

    public LogFormatter getFormatter() {
        return formatter;
    }
}
