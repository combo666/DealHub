module com.example.dealhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.dealhub to javafx.fxml;
    exports com.dealhub;
}