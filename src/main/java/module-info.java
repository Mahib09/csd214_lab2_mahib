module com.example.lab2test {
    requires javafx.controls;
    requires javafx.fxml;
            
                                requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.csd214_lab2_mahib to javafx.fxml;
    exports com.example.csd214_lab2_mahib;
}