/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifcationexamples;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import taskers.*;

/**
 * FXML Controller class
 *
 * @author dalemusser
 */
public class NotificationsUIController implements Initializable, Notifiable {

    @FXML
    private TextArea textArea;
    
    @FXML
    private Button task1Button;
    @FXML
    private Button task2Button;
    @FXML
    private Button task3Button;
    
    private Task1 task1;
    private Task2 task2;
    private Task3 task3;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void start(Stage stage) {
        task1Button.setStyle("-fx-border-color: #008000;"
                + "-fx-border-width: 4px;"
                + "-fx-border-radius: 4px;"
                + "-fx-background-radius: 10px;"
                + "-fx-focus-color: transparent;");
        task2Button.setStyle("-fx-border-color: #008000;"
                + "-fx-border-width: 4px;"
                + "-fx-border-radius: 4px;"
                + "-fx-background-radius: 10px;"
                + "-fx-focus-color: transparent;");
        task3Button.setStyle("-fx-border-color: #008000;"
                + "-fx-border-width: 4px;"
                + "-fx-border-radius: 4px;"
                + "-fx-background-radius: 10px;"
                + "-fx-focus-color: transparent;");
        
        stage.setOnCloseRequest((WindowEvent we) -> {
            if (task1 != null) task1.end();
            if (task2 != null) task2.end();
            if (task3 != null) task3.end();
        });
    }
    
    @FXML
    public void task1Click(ActionEvent event) {
        if (task1 == null || task1.getState() == Task1.State.TERMINATED) {
            System.out.println("Task 1 started.");
            task1Button.setText("End Task 1");
            task1Button.setStyle("-fx-border-color: #ff0000;"
                    + "-fx-border-width: 4px;"
                    + "-fx-border-radius: 4px;"
                    + "-fx-background-radius: 10px;"
                    + "-fx-focus-color: transparent;");
            task1 = new Task1(2147483647, 1000000, this);
            task1.setNotificationTarget(this);
            task1.start();
        } else if(task1 != null && task1.getState() == Task1.State.RUNNABLE) {
            System.out.println("Task 1 stopped.");
            task1Button.setText("Start Task 1");
            task1Button.setStyle("-fx-border-color: #008000;"
                    + "-fx-border-width: 4px;"
                    + "-fx-border-radius: 4px;"
                    + "-fx-background-radius: 10px;"
                    + "-fx-focus-color: transparent;");
            task1.end();
        }
    }
    
    @Override
    public void notify(String message) {
        if (message.equals("Task1 done.")) {
            task1 = null;
        }
        textArea.appendText(message + "\n");
    }
    
    @FXML
    public void task2Click(ActionEvent event) {
        if (task2 == null || task2.getState() == Task2.State.TERMINATED) {
            System.out.println("Task 2 started.");
            task2Button.setText("End Task 2");
            task2Button.setStyle("-fx-border-color: #ff0000;"
                    + "-fx-border-width: 4px;"
                    + "-fx-border-radius: 4px;"
                    + "-fx-background-radius: 10px;"
                    + "-fx-focus-color: transparent;");
            task2 = new Task2(2147483647, 1000000, this);
            task2.setOnNotification((String message) -> {
                textArea.appendText(message + "\n");
            });
            task2.start();
        } else if(task2 != null && task2.getState() == Task2.State.RUNNABLE) {
            System.out.println("Task 2 stopped.");
            task2Button.setText("Start Task 2");
            task2Button.setStyle("-fx-border-color: #008000;"
                    + "-fx-border-width: 4px;"
                    + "-fx-border-radius: 4px;"
                    + "-fx-background-radius: 10px;"
                    + "-fx-focus-color: transparent;");
            task2.end();
        }
    }
    
    @FXML
    public void task3Click(ActionEvent event) {
        if (task3 == null || task3.getState() == Task3.State.TERMINATED) {
            System.out.println("Task 3 started.");
            task3Button.setText("End Task 3");
            task3Button.setStyle("-fx-border-color: #ff0000;"
                    + "-fx-border-width: 4px;"
                    + "-fx-border-radius: 4px;"
                    + "-fx-background-radius: 10px;"
                    + "-fx-focus-color: transparent;");
            task3 = new Task3(2147483647, 1000000, this);
            // this uses a property change listener to get messages
            task3.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                textArea.appendText((String)evt.getNewValue() + "\n");
            });
            task3.start();
        } else if (task3 != null && task3.getState() == Task3.State.RUNNABLE) {
            System.out.println("Task 3 stopped.");
            task3Button.setText("Start Task 3");
            task3Button.setStyle("-fx-border-color: #008000;"
                    + "-fx-border-width: 4px;"
                    + "-fx-border-radius: 4px;"
                    + "-fx-background-radius: 10px;"
                    + "-fx-focus-color: transparent;");
            task3.end();
        }
    }
    
    public void task1Complete() {
        task1Button.setText("Start Task 1");
        task1Button.setStyle("-fx-border-color: #008000;"
                + "-fx-border-width: 4px;"
                + "-fx-border-radius: 4px;"
                + "-fx-background-radius: 10px;"
                + "-fx-focus-color: transparent;");
    }
    
    public void task2Complete() {
        task2Button.setText("Start Task 2");
        task2Button.setStyle("-fx-border-color: #008000;"
                + "-fx-border-width: 4px;"
                + "-fx-border-radius: 4px;"
                + "-fx-background-radius: 10px;"
                + "-fx-focus-color: transparent;");
    }
    
    public void task3Complete() {
        task3Button.setText("Start Task 3");
        task3Button.setStyle("-fx-border-color: #008000;"
                + "-fx-border-width: 4px;"
                + "-fx-border-radius: 4px;"
                + "-fx-background-radius: 10px;"
                + "-fx-focus-color: transparent;");
    }
}
