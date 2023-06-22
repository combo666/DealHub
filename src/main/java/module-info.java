module com.example.dealhub {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.dealhub to javafx.fxml;
    exports com.dealhub;
}