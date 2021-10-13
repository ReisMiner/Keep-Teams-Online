module xyz.reisminer.keepteamsonline {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens xyz.reisminer.keepteamsonline to javafx.fxml;
    exports xyz.reisminer.keepteamsonline;
}