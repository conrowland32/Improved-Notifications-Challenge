/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskers;

import javafx.application.Platform;
import notifcationexamples.NotificationsUIController;

/**
 *
 * @author dalemusser
 * 
 * This example uses an object passed in with a notify()
 * method that gets called when a notification is to occur.
 * To accomplish this the Notifiable interface is needed.
 * 
 */
public class Task1 extends Thread {
    
    private int maxValue, notifyEvery;
    boolean exit = false;
    private boolean finished = false;
    
    private Notifiable notificationTarget;
    private NotificationsUIController controller;
    
    public Task1(int maxValue, int notifyEvery, NotificationsUIController controller)  {
        this.maxValue = maxValue;
        this.notifyEvery = notifyEvery;
        this.controller = controller;
    }
    
    @Override
    public void run() {
        doNotify("Task 1 started.");
        for (int i = 0; i < maxValue; i++) {
            
            if (i % notifyEvery == 0) {
                doNotify("It happened in Task 1: " + i);
            }
            
            if (exit) {
                return;
            }
        }
        System.out.println("Task 1 complete.");
        doNotify("Task 1 complete.");
        Platform.runLater(() -> {
            controller.task1Complete();
        });
    }
    
    public void end() {
        doNotify("Task 1 stopped.");
        exit = true;
    }
    
    public void setNotificationTarget(Notifiable notificationTarget) {
        this.notificationTarget = notificationTarget;
    }
    
    private void doNotify(String message) {
        // this provides the notification through a method on a passed in object (notificationTarget)
        if (notificationTarget != null) {
            Platform.runLater(() -> {
                notificationTarget.notify(message);
            });
        }
    }
}
