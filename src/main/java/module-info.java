module asia.sustech.happymatch {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens asia.sustech.happymatch to javafx.fxml;
    exports asia.sustech.happymatch;
}