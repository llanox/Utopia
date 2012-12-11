package co.edu.udea.ludens.applet.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataUpdater extends Thread {
    Updatable target;
    private int delay = 1000;

    public DataUpdater(Updatable target, int delay) {
        this.target = target;
        this.delay = delay;
    }

    @Override()
    public void run() {
        while (true) {
            if (!target.isStopped()) {
                try {
                    Thread.sleep(delay);
                    target.updatingProcess();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DataUpdater.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        }
    }
}