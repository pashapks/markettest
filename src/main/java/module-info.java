module com.market.megamarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.market.megamarket to javafx.fxml;
    exports com.market.megamarket;
}