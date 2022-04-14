package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * @author Fiona Li-Duong
*/


public abstract class ListManager {
    @FXML private AnchorPane rightAnchorPane;
    @FXML private ImageView imageIcon;
    @FXML private Label sentence1;
    @FXML private Label sentence2;

    @FXML private AnchorPane addTaskAnchorPane;
    @FXML private AnchorPane addTaskPopUpButton;

    @FXML private AnchorPane completedLabelAnchor;
    @FXML private AnchorPane importantLabelAnchor;
    @FXML private AnchorPane todayLabelAnchor;
    @FXML private AnchorPane upcomingLabelAnchor;

    @FXML private TextField taskInput;
    @FXML private DatePicker dateInput;
    @FXML private Button addTaskButton;

    @FXML private TableView<TaskItem> tableView;
    @FXML private TableColumn<TaskItem, LocalDate> dateColumn;
    @FXML private TableColumn<TaskItem, String> taskColumn;  
    @FXML private TableColumn<TaskItem, String> deleteColumn;  
    @FXML private TableColumn<TaskItem, String> statusColumn;  

    FileManager fileManager = new FileManager();
    ObservableList<TaskItem> taskList = FXCollections.observableArrayList();

    /**
     * used to hide the message that is displayed when list is empty
     */
    protected void hideEmptyListMessage() {
        imageIcon.setOpacity(0);
        sentence1.setOpacity(0);
        sentence2.setOpacity(0);;
    }

    /**
     * used to show the message that is displayed when list is empty
     */
    protected void showEmptyListMessage(){
        imageIcon.setOpacity(100);
        sentence1.setOpacity(100);
        sentence2.setOpacity(100);
    }

    /**
     * used to remove the user input on the textfield and replace with the text prompt
     */
    protected void refreshTaskInput(){
        taskInput.setText(null);
    }

    /**
     * used to show the addTask menu pane onto the view
     * and to hide the button to show the addtask menu pane
     */
    protected void showAddTaskMenuPane() {
      addTaskAnchorPane.setOpacity(1);
      addTaskAnchorPane.setTranslateY(0);
      addTaskPopUpButton.setOpacity(0);
      addTaskPopUpButton.setTranslateY(60);
   }

   /**
    * used to hide the add task menu pane in the view 
    * and to show the button to show the addtask menu pane
    */
    protected void hideAddTaskMenuPane() {
        addTaskAnchorPane.setOpacity(1);
        addTaskAnchorPane.setTranslateY(65);
        addTaskPopUpButton.setOpacity(1);
        addTaskPopUpButton.setTranslateY(0);
    }

    /**
     * used to add a task to the taskList 
     * @param date - Date object containing date information to be added to the taskItem and list
     */
    protected void addTaskToTaskList(LocalDate date) {
      ObservableList<TaskItem> items = tableView.getItems();

        try {
          if(!taskInput.getText().isEmpty() && date != null){
            TaskItem newtasklistEvent = new TaskItem(taskInput.getText(), date);
            tableView.getItems().add(newtasklistEvent);
          }
        } catch (Exception e) {
          if (items.isEmpty()){
            showEmptyListMessage();
            System.out.println("Must fill in all information");
          } else {
            System.out.println("Must fill in all information");
          }
        }
    }

    /***
     * used to add a task to the today task list
     * @param todayDate - Date object containing the current date information to be added to the taskItem and the list
     */
    protected void addTaskToTodayList(LocalDate todayDate) {
      ObservableList<TaskItem> items = tableView.getItems();

        try {
          if(!taskInput.getText().isEmpty()){
            TaskItem newtasklistEvent = new TaskItem(taskInput.getText(), todayDate);
            tableView.getItems().add(newtasklistEvent);
          }
        } catch (Exception e) {
          if (items.isEmpty()){
            showEmptyListMessage();
            System.out.println("Must fill in all information");
          } else {
            System.out.println("Must fill in all information");
          }
        }
    }  

    /**
     * used to set up the TableView table cells
     * also sets the CSS style names for the date and task column
     */
    protected void setTableCellValueFactory(){
        dateColumn.getStyleClass().add("date-column");
        taskColumn.getStyleClass().add("task-column");

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("task"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.setPlaceholder(new Label(""));
    }

    /**
     * used to load the task lists from the file and to inflate item objects and add to the observableList
     * @param FILE - String object containing the information for the path to the file on disk
     * @param taskList - ObservableList object to collect the reinflated item objects from the file
     */
    protected void loadTaskListFromFile(String FILE, ObservableList<TaskItem> taskList) {
        ObservableList<TaskItem> loadedTaskList;
        try {
          loadedTaskList = fileManager.loadListsFromFile(new File(FILE), taskList);
          if (loadedTaskList.isEmpty()){
            showEmptyListMessage(); 
          } else {
            tableView.setItems(loadedTaskList);
            hideEmptyListMessage();
          }
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
    }

    /**
     * used to add a delete button to the TablieView
     * @param FILE - String object containing the information for the path to the file on disk
     * @param taskList - ObservableList object containing information on the tasklist passed
     */
    protected void addDelButtonToTable(String FILE, ObservableList<TaskItem> taskList) {
        TableColumn<TaskItem, Void> colBtn = new TableColumn("x");
        Callback<TableColumn<TaskItem, Void>, TableCell<TaskItem, Void>> cellFactory = new Callback<TableColumn<TaskItem, Void>, TableCell<TaskItem, Void>>() {
            
          @Override
            public TableCell<TaskItem, Void> call(final TableColumn<TaskItem, Void> param) {
                final TableCell<TaskItem, Void> cell = new TableCell<TaskItem, Void>() {
  
                    private final Button btn = new Button("x");
                    {   btn.setOnAction((ActionEvent event) -> {
                            TaskItem task = getTableView().getItems().get(getIndex());
                            System.out.println("Removing selected task row: " + task.getTask() + ", " + task.getDate());
                            tableView.getItems().remove(task);
  
                            taskList.remove(task);
                            try {
                              fileManager.writeListsToFile(new File(FILE), taskList);
                            } catch (FileNotFoundException e) {
                              e.printStackTrace();
                            }

                            //if list is empty again, show empty list message!!!
                            if (taskList.isEmpty()){
                              showEmptyListMessage();
                            }
                        });
                    }
  
                    @Override
                    public void updateItem(Void item, boolean empty){
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
  
                return cell;
            }
        };
  
        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().add(colBtn);
  
    }
  
}
