module asia.sustech.happymatch {
    requires javafx.controls;
    requires javafx.fxml;
    requires fastjson;
    requires java.desktop;
    requires javafx.media;


    opens asia.sustech.happymatch to javafx.fxml;
    exports asia.sustech.happymatch;
    exports asia.sustech.happymatch.GameController;
    opens asia.sustech.happymatch.GameController to javafx.fxml;
    exports asia.sustech.happymatch.Login;
    opens asia.sustech.happymatch.Login to javafx.fxml;
    exports asia.sustech.happymatch.Hall;
    opens asia.sustech.happymatch.Hall to javafx.fxml;
    exports asia.sustech.happymatch.Ending;
    opens asia.sustech.happymatch.Ending to javafx.fxml;
    exports asia.sustech.happymatch.Utils;
    opens asia.sustech.happymatch.Utils to javafx.fxml;
}