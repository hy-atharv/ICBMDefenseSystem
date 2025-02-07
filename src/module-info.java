module ICBM_DefenseSystemModule {
	requires javafx.base;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.web;
	requires javafx.graphics;
	
	
	opens application to javafx.graphics, javafx.fxml;
}
