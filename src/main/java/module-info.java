module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
    requires spring.context;
    requires spring.web;
    requires com.fasterxml.jackson.annotation;
    requires org.apache.logging.log4j;


    opens app to javafx.fxml;
    exports app;
    opens ViewModel to javafx.fxml;
    exports ViewModel;
    exports Controller;
    exports Model;
    opens Model to spring.core, com.fasterxml.jackson.databind;
    opens Controller to javafx.fxml;
}