module main.frontend {
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
    requires org.postgresql.jdbc;

    // Export the main.frontend package
    exports main.frontend;

    // Open the main.frontend.controller package to javafx.fxml and javafx.base
    opens main.frontend.controller to javafx.fxml, javafx.base;
}
