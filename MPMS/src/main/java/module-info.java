module org.menter.application.mpms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.menter.application.mpms to javafx.fxml;
    exports org.menter.application.mpms;
}