module com.jocelyn.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

//    opens com.jocelyn.inventorymanagementsystem to javafx.fxml;
//    exports com.jocelyn.inventorymanagementsystem;
    exports com.jocelyn.inventorymanagementsystem.controller;
    opens com.jocelyn.inventorymanagementsystem.controller to javafx.fxml;
    opens com.jocelyn.inventorymanagementsystem.modal to javafx.base;
}