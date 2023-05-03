package com.market.megamarket;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import java.sql.SQLException;


public class ProductList {
    @FXML
    private Button buttonAdd;

    @FXML
    private TextField addTextProduct;

    @FXML
    private VBox itemsBox;

    @FXML
    private Label totalPrice;

    @FXML
    private Label totalQuantity;

    @FXML
    private AnchorPane helpPane;

    @FXML
    void initialize() {
        buttonAdd.setOnAction(event -> {
            addTextProduct.setVisible(true);
            buttonAdd.setVisible(false);
            helpPane.setVisible(false);
        });
        Item.takeTotalPriceLabel(totalPrice);
        Item.takeTotalQuantityLabel(totalQuantity);
    }

    @FXML
    void addItem(){
        addTextProduct.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                Item viewModel = null;
                try {
                    viewModel = new Item(addTextProduct.getText(), itemsBox);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    viewModel.addItem(addTextProduct, itemsBox);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                buttonAdd.setVisible(true);
                addTextProduct.setVisible(false);
                addTextProduct.clear();
            }
    });
    }
}
