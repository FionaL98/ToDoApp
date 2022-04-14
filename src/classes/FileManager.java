package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import javafx.collections.ObservableList;

/**
 * @author Fiona Li-Duong
 */

public class FileManager {

    /**
     * used to persist taskItems from list to file on disk
     * @param file - File object containing path to desired file on disk
     * @param list - observableList of taskItems to be persisted to disk
     * @throws FileNotFoundException
     */
    public void writeListsToFile(File file, ObservableList<TaskItem> list) throws FileNotFoundException{
        try (PrintWriter printWriter = new PrintWriter(file)){
            for (TaskItem tasks : list){
                printWriter.println(tasks.getTask() + "," + tasks.getDate());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR!! file not found!");
            System.exit(1);
        }
    }

    /**
     * used to read CSV file from disk and use that date to reinflate taskItem objects and add to observableList<TaskItem> tasks
     * @param file - File object containing path to desired file on disk
     * @param tasks - ObservableList object to collect the reinflated item objects from the file
     * @return ObservableList<TaskItem> list of taskItem objects in ObservableList<TaskItem>
     * @throws FileNotFoundException
     */
    public ObservableList<TaskItem> loadListsFromFile(File file, ObservableList<TaskItem> tasks) throws FileNotFoundException {
        try (Scanner scan = new Scanner(file)){
            while(scan.hasNextLine()){
                String[] taskParts = scan.nextLine().split(",");
                tasks.add(new TaskItem(taskParts[0], LocalDate.parse(taskParts[1])));
            }
        } catch(FileNotFoundException e){
            System.out.println("ERROR!!! file not found!");
            System.exit(1);
        }
        return tasks;
    }



}

