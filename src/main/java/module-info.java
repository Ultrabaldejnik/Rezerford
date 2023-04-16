module com.example.rezerford {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rezerford to javafx.fxml;
    exports com.example.rezerford;
}