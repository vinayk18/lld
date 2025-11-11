public enum LogLevel {

    INFO(1),
    DEBUG(2),
    ERROR(3);

    private final int priority;

    LogLevel( int p ){
        this.priority = p;
    }

    public int getPriority(){
        return priority;
    }
}
