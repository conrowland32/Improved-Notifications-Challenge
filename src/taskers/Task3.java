/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.application.Platform;
import notifcationexamples.NotificationsUIController;

/**
 *
 * @author dalemusser
 * 
 * This example uses PropertyChangeSupport to implement
 * property change listeners.
 * 
 */
public class Task3 extends Thread {
    
    private int maxValue, notifyEvery;
    boolean exit = false;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    private NotificationsUIController controller;
    
    public Task3(int maxValue, int notifyEvery, NotificationsUIController controller)  {
        this.maxValue = maxValue;
        this.notifyEvery = notifyEvery;
        this.controller = controller;
    }
    
    @Override
    public void run() {
        doNotify("Task 3 started.");
        for (int i = 0; i < maxValue; i++) {
            
            if (i % notifyEvery == 0) {
                doNotify("It happened in Task 3: " + i);
            }
            
            if (exit) {
                return;
            }
        }
        System.out.println("Task 3 complete.");
        doNotify("Task 3 complete.");
        Platform.runLater(() -> {
            controller.task3Complete();
        });
    }
    
    public void end() {
        doNotify("Task 3 stopped.");
        exit = true;
    }
    
    // the following two methods allow property change listeners to be added
    // and removed
    public void addPropertyChangeListener(PropertyChangeListener listener) {
         pcs.addPropertyChangeListener(listener);
     }

     public void removePropertyChangeListener(PropertyChangeListener listener) {
         pcs.removePropertyChangeListener(listener);
     }
    
    private void doNotify(String message) {
        // this provides the notification through the property change listener
        Platform.runLater(() -> {
            // I'm choosing not to send the old value (second param).  Sending "" instead.
            pcs.firePropertyChange("message", "", message);
        });
    }
}