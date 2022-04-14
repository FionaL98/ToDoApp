package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import classes.FileManager;
import classes.ListManager;
import classes.TaskItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Fiona Li-Duong
*/

public class todayPaneController extends ListManager implements Initializable{

    @FXML private ImageView imageIcon;
    @FXML private Label sentence1;
    @FXML private Label sentence2;

    @FXML private AnchorPane addTaskAnchorPane;
    @FXML private AnchorPane addTaskPopUpButton;

    @FXML private TextField taskInput;
    @FXML private DatePicker dateInput;
    @FXML private Button addTaskButton;

    @FXML private TableView<TaskItem> tableView;
    @FXML private TableColumn<TaskItem, LocalDate> dateColumn;
    @FXML private TableColumn<TaskItem, String> taskColumn;  
    @FXML private TableColumn<TaskItem, String> deleteColumn;  
    @FXML private TableColumn<TaskItem, String> editColumn;   
    @FXML private TableColumn<TaskItem, String> statusColumn;  

    FileManager fileManager = new FileManager();
    LocalDate todayDate = LocalDate.now();
    private final String FILE = "src/lists/todayList.txt";
    ObservableList<TaskItem> taskList = FXCollections.observableArrayList();

    @FXML
    void addTaskInputEvent(ActionEvent event) throws FileNotFoundException {
        hideEmptyListMessage();
        addTaskToTodayList(todayDate);
        
        fileManager.writeListsToFile(new File(FILE), taskList);
        refreshTaskInput();
    }

    @FXML
    void editTaskColumnEvent(CellEditEvent<TaskItem, String> editedTaskCell) throws FileNotFoundException {
        TaskItem taskSelected = tableView.getSelectionModel().getSelectedItem(); 
        TaskItem editedTask = taskSelected;
        editedTask.setTask(editedTaskCell.getNewValue().toString()); 

        int tempIndex = taskList.indexOf(taskSelected);
        taskList.set(tempIndex, editedTask);
        fileManager.writeListsToFile(new File(FILE), taskList);
    }
    
    @FXML void addTaskPopUpOnClicked(MouseEvent event) {
        showAddTaskMenuPane();
    }

    @FXML void addTaskPopUpBtnMouseEntered(MouseEvent event) {
        addTaskPopUpButton.setOpacity(1);
    }

    @FXML void addTaskPopUpBtnMouseExit(MouseEvent event) {
        addTaskPopUpButton.setOpacity(0.4);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        tableView.setEditable(true);
        taskColumn.setCellFactory(TextFieldTableCell.forTableColumn()); 

        setTableCellValueFactory();
        tableView.setItems(taskList);

        loadTaskListFromFile(FILE, taskList);
        addDelButtonToTable(FILE, taskList);

        hideAddTaskMenuPane();
        addTaskPopUpButton.setOpacity(0.4);
    }



}

