package classes;

import java.time.LocalDate;
import javafx.scene.control.CheckBox;

/**
 * @author Fiona Li-Duong
 */

public class TaskItem {
    private String task; 
    private LocalDate date;
    private CheckBox checkbox;
    private String status;

    /**
     * Constructor
     * @param task - String containing task information to be added to taskItem
     * @param date - Date object containing date information to be added to taskItem
     */

    public TaskItem(String task, LocalDate date){
        this.checkbox = new CheckBox();
        this.task = task;
        this.date = date;
    }

    @Override
    public String toString(){
        return this.task;
    }

    public String getText() {
        return null;
    }

    public boolean getCompleted() {
        return false;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTask() {
        return this.task;
    }

    public CheckBox getCheckbox(){
        return checkbox;
    }

    public void setCheckbox(){
        this.checkbox = new CheckBox();
    }

    public String getStatus(){
        return status;
    }
  
    public void setStatus(String status){
      this.status = status;
    }

}
