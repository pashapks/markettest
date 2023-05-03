package com.market.megamarket;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartMenuController {
    @FXML
    public Button closeButton;

    @FXML
    private CheckBox agreeCheck;

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;

    @FXML
    private AnchorPane onNoPane;

    public void switchToShopScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("productList.fxml"));
        Stage stage = (Stage) yesButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 420, 660);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        assert agreeCheck != null : "fx:id=\"agreeCheck\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert noButton != null : "fx:id=\"noButton\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert yesButton != null : "fx:id=\"yesButton\" was not injected: check your FXML file 'startMenu.fxml'.";

    }

    @FXML
    void yesButtonClick() throws IOException {
        if (agreeCheck.isSelected()){
            System.out.println(agreeCheck.isSelected());
            switchToShopScene();
        }
    }

    @FXML
    void noButtonClick() {
        onNoPane.setVisible(true);
    }

    @FXML
    void closeButtonClick(){
        onNoPane.setVisible(false);
    }
}
