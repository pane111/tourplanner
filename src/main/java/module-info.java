module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires spring.context;
    requires spring.web;
    requires org.apache.logging.log4j;
    requires jakarta.annotation;
    requires com.fasterxml.jackson.databind;
    requires jdk.compiler;
    requires java.desktop;
    requires spring.beans;
    requires kernel;
    requires layout;
    requires io;
    requires com.opencsv;
    requires java.sql;


    opens app to javafx.fxml;
    exports app;
    opens ViewModel to javafx.fxml;
    exports ViewModel;
    exports Controller;
    exports Model;
    opens Model to spring.core, com.fasterxml.jackson.databind;
    opens Controller to javafx.fxml;
}