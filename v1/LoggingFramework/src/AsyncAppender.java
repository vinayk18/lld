import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class AsyncAppender implements Appender{

    private final Appender delegate;
    private final BlockingQueue<String> queue;
    private final Thread worker;
    private volatile boolean running = true;

    public AsyncAppender(Appender delegate, Appender delegate1, BlockingQueue<String> queue, Thread worker){
        this.delegate = delegate1;
        this.worker = worker;
        this.queue = new LinkedBlockingDeque<>(10000);

    }

    private void processQueue(){
        while( running || !queue.isEmpty() ){
            try {
                String msg = queue.poll(500, TimeUnit.MILLISECONDS);
                if( msg != null ) delegate.append(msg);
            }
            catch ( Exception e ) {
                System.out.println("Async Appender failed : " + e.getMessage() );
            }
        }
    }

    @Override
    public void append(String message) {
        if(!queue.offer(message)){
            queue.poll();
            queue.offer(message);
        }
    }
}
