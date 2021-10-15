import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.ConfigurationProject;

import java.io.IOException;

public class Run extends Application {
    @Override
    public void start(Stage introStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
            "display/graphic/intro/Intro.fxml"));
        Scene scene = new Scene(root);
        ConfigurationProject.introStage.setScene(scene);
        ConfigurationProject.introStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
