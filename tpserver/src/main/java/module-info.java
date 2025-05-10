module com.fhtw.tpserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;


    opens com.fhtw.tpserver to javafx.fxml;
    exports com.fhtw.tpserver;
    exports com.fhtw.tpserver.controller;
    opens com.fhtw.tpserver.controller to javafx.fxml;
}