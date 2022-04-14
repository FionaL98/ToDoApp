package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * @author Fiona Li-Duong
*/

public class mainViewController implements Initializable{

    @FXML private AnchorPane rightAnchorPane;

    @FXML private AnchorPane importantLabelAnchor;
    @FXML private AnchorPane todayLabelAnchor;
    @FXML private AnchorPane upcomingLabelAnchor;

    @FXML private Label titleTEEDEE;
    @FXML private Label thankYouMessage;
    @FXML private AnchorPane listChangeAnchorPane;
    @FXML private AnchorPane menuAnchor;
    @FXML private AnchorPane menuIconAnchor;
    @FXML private AnchorPane menuPane;
    @FXML private AnchorPane goBackAnchor;


    @FXML void goBackAnchorOnMouseClicked(MouseEvent event) {
        hideMenuPaneFromView();
    }

    @FXML void menuIconAnchorOnMouseClicked(MouseEvent event) throws IOException {
        showMenuPaneInView();
    }
 
    @FXML void TeeDeeOnClicked(MouseEvent event) {
        titleTEEDEE.setTextFill(Color.color(Math.random(), Math.random(), Math.random()));
    }

    /**
     * used to hide the menu pane
     */
    private void hideMenuPaneFromView() {
        menuPane.setOpacity(0);
        menuPane.setTranslateX(-160);
        titleTEEDEE.setOpacity(1);;
        listChangeAnchorPane.setOpacity(1);
        thankYouMessage.setOpacity(0);
    }

    /**
     * used to show the menu pane
     */
    private void showMenuPaneInView() {
        menuPane.setOpacity(1);
        menuPane.setTranslateX(0);
        titleTEEDEE.setOpacity(0.1);;
        listChangeAnchorPane.setOpacity(0.1);
        thankYouMessage.setOpacity(0.5);
    }

    // ============================================================================================
    //to navigate to different categories for ToDO App

    @FXML
    void importantLabelAnchorClicked(MouseEvent event) throws IOException {
        hideMenuPaneFromView();
        AnchorPane importantPane = FXMLLoader.load(getClass().getResource("/guibuilds/importantPane.fxml"));
        listChangeAnchorPane.getChildren().setAll(importantPane);
    }

    @FXML
    void todayLabelAnchorClicked(MouseEvent event) throws IOException {
        hideMenuPaneFromView();
        AnchorPane todayPane = FXMLLoader.load(getClass().getResource("/guibuilds/todayPane.fxml"));
        listChangeAnchorPane.getChildren().setAll(todayPane);
    }

    @FXML
    void upcomingLabelAnchorClicked(MouseEvent event) throws IOException {
        hideMenuPaneFromView();
        AnchorPane upcomingPane = FXMLLoader.load(getClass().getResource("/guibuilds/upcomingPane.fxml"));
        listChangeAnchorPane.getChildren().setAll(upcomingPane);
    }

    //=============================================================================
    //mouse enter and exit for hover color effect on category labels and menu icon
    
    @FXML void todayLabelAnchorMouseEntered(MouseEvent event) {
        todayLabelAnchor.setStyle("-fx-background-color: #5f5766");
    }

    @FXML void todayLabelAnchorMouseExit(MouseEvent event) {
        todayLabelAnchor.setStyle("-fx-background-color:  #766d7d");
    }

    @FXML void upcomingLabelAnchorMouseEntered(MouseEvent event) {
        upcomingLabelAnchor.setStyle("-fx-background-color: #5f5766");
    }

    @FXML void upcomingLabelAnchorMouseExit(MouseEvent event) {
        upcomingLabelAnchor.setStyle("-fx-background-color:  #766d7d");
    }

    @FXML void importantLabelAnchorMouseEntered(MouseEvent event) {
        importantLabelAnchor.setStyle("-fx-background-color: #5f5766");
    }

    @FXML void importantLabelAnchorMouseExit(MouseEvent event) {
        importantLabelAnchor.setStyle("-fx-background-color:  #766d7d");
    }

    @FXML void menuIconAnchorMouseEnter(MouseEvent event) {
        menuIconAnchor.setOpacity(1);
    }

    @FXML void menuIconAnchorMouseExit(MouseEvent event) {
        menuIconAnchor.setOpacity(0.4);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPane.setOpacity(0);
        menuPane.setTranslateX(-160);

        menuIconAnchor.setOpacity(0.4);
    }

}
