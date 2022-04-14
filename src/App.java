
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Fiona Li-Duong
*/

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/guibuilds/mainView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("T E E D E E L I S T");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
