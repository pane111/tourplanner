module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens app to javafx.fxml;
    exports app;
    exports View;
    opens View to javafx.fxml;
    exports ViewModel to javafx.fxml;
    opens ViewModel to javafx.fxml;
}