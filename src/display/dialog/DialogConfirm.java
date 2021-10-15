package display.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogConfirm {

  Alert notification = new Alert(Alert.AlertType.CONFIRMATION);

  public boolean show(String title, String text) {
    notification.setHeaderText(null);
    notification.setContentText(text);
    notification.setTitle(title);
    Optional<ButtonType> buttonConfi = notification.showAndWait();
    return buttonConfi.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
  }
}
