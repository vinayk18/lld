package CoffeeMachine;

import java.util.concurrent.Semaphore;

public class OutletManager {

    private final Semaphore outlets;

    public OutletManager(int outlets) {
        this.outlets = new Semaphore(outlets);
    }

    public void acquireOutlet() throws InterruptedException {
        outlets.acquire();
    }

    public void releaseOutlet(){
        outlets.release();
    }
}
