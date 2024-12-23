module org.menter.application.mpms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.sql; // Include this for JDBC classes

    opens org.menter.application.mpms.entity to javafx.base;
    opens org.menter.application.mpms to javafx.fxml;
    exports org.menter.application.mpms;
}