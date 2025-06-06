module org.example.jobswapsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens org.example.jobswapsystem to javafx.fxml;
    exports org.example.jobswapsystem;
    exports org.example.jobswapsystem.Service;
    exports org.example.jobswapsystem.Models;
}