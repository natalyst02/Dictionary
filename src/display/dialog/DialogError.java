package display.dialog;

import javafx.scene.control.Alert;

public class DialogError {

  Alert notification = new Alert(Alert.AlertType.ERROR);

  public void show(String title, String text) {
    notification.setHeaderText(null);
    notification.setContentText(text);
    notification.setTitle(title);
    notification.showAndWait();
  }
}
