public abstract class LogHandler {

    protected LogHandler next;

    public void setNext( LogHandler nexHandler ){
        this.next = nexHandler;
    }

    public void handle(LogLevel level,String message){
        if( canHandle(level)){
            write(message);
        }
        if( next != null ){
            next.handle(level,message);
        }
    }

    protected abstract boolean canHandle(LogLevel level);
    protected abstract void write(String message);

}
