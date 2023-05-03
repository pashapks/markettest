package com.market.megamarket;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.sql.SQLException;
import java.util.ArrayList;

public class Item {
    private static final ArrayList<Item> items = new ArrayList<>();
    private static int itemCount = 0;
    private final String name;
    private int quantity = 1;
    private final double price;
    private int number;
    private static Label totalPriceLabel;
    private static Label totalQuantityLabel;
    String url = "jdbc:postgresql://localhost:5432/dbtest";
    String user = "postgres";
    String password = "root";
    PostgreSQLHandler handler = new PostgreSQLHandler(url, user, password);
    private final Label nameLabel = new Label();
    private final Label quantityLabel = new Label();
    private final Label priceLabel = new Label();
    private final Button decreaseButton = new Button("-");
    private final Button increaseButton = new Button("+");
    private final Button removeButton = new Button("X");

    public Item(String id, VBox itemsBox) throws SQLException {
        int id1 = Integer.parseInt(id);
        this.name = handler.getNameById(id1);
        this.price = handler.getPriceById(id1);
        nameLabel.setText(number + ". " + name);
        nameLabel.setPrefSize(190, 25);
        quantityLabel.setText(quantity + " шт");
        quantityLabel.setPrefSize(40,25);
        quantityLabel.setStyle("-fx-alignment: center");
        priceLabel.setText(Double.toString(price * quantity));
        priceLabel.setPrefSize(60, 25);
        priceLabel.setStyle("-fx-alignment: center");

        decreaseButton.setOnAction(event -> {
            if (quantity > 1) {
                quantity--;
                quantityLabel.setText(quantity + " шт");
                priceLabel.setText(Double.toString(price * quantity));
                updateItems();
            }
        });

        increaseButton.setOnAction(event -> {
            quantity++;
            quantityLabel.setText(quantity + " шт");
            priceLabel.setText(Double.toString(price * quantity));
            updateItems();
        });

        removeButton.setOnAction(event -> {
            itemsBox.getChildren().remove(getView());
            items.remove(this);
            itemCount--;
            updateItems();
        });
    }

    public void setNumber(int number) {
        this.number = number;
        nameLabel.setText(number + ". " + name);
    }

    public HBox getView() {
        HBox view = new HBox();
        decreaseButton.setPrefSize(25, 25);
        decreaseButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
        increaseButton.setPrefSize(25, 25);
        increaseButton.setStyle("-fx-background-color: green; -fx-text-fill: white");
        removeButton.setPrefSize(25, 25);
        removeButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
        view.getChildren().addAll(nameLabel, decreaseButton, quantityLabel, increaseButton, priceLabel, removeButton);
        return view;
    }

    public static void updateItems() {
        double totalPrice = 0.0;
        int totalQuantity = 0;
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            item.setNumber(i + 1);
            totalPrice += items.get(i).quantity * items.get(i).price;
            totalQuantity += items.get(i).quantity;
        }
        totalPriceLabel.setText(totalPrice + " руб");
        totalQuantityLabel.setText(totalQuantity + " шт");
    }

    public void addItem(TextField textField, VBox itemsBox) throws SQLException {
                String id = textField.getText();
                Item item = new Item(id, itemsBox);
                items.add(item);
                itemsBox.getChildren().add(item.getView());
                itemCount++;
                updateItems();
    }


    public static void takeTotalPriceLabel(Label label){
        totalPriceLabel = label;
    }

    public static void takeTotalQuantityLabel(Label label){
        totalQuantityLabel = label;
    }
}
