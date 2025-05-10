module com.fhtw.tpserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.fhtw.tpserver to javafx.fxml;
    exports com.fhtw.tpserver;
}