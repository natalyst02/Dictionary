package display.graphic.editword;

import display.dialog.DialogConfirm;
import display.dialog.DialogInfor;
import display.graphic.intro.IntroController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import utility.ConfigurationProject;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;


public class EditController extends IntroController implements Initializable {
  @FXML
  private HTMLEditor htmlEditor;

  public void setintroStage() throws IOException {
    ConfigurationProject.introStage.setScene(IntroController.showScene());
  }
  public void setButtonBack() throws IOException {
    DialogConfirm cancelNotification = new DialogConfirm();
    boolean checkNoti = cancelNotification.show("Add New Word",
        "Do you want to back?");
    if (checkNoti) {
      setintroStage();
    }
  }

  public void setButtonSave() throws IOException {
    DialogConfirm editWordConfirm = new DialogConfirm();
    boolean checkNoti = editWordConfirm.show("Edit Word",
        "Do you want to edit this word?");
    if (checkNoti) {
      OurDictionary.editWord(directWord, htmlEditor.getHtmlText());
      DialogInfor saveNoti = new DialogInfor();
      saveNoti.show("Edit Word", "Edited successfully");
      setintroStage();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String html = OurDictionary.LookupDic(directWord);
    htmlEditor.setHtmlText(html);
  }

  public static Scene showScene() throws IOException {
    Parent root = FXMLLoader.load(EditController.class.getResource("EditWord.fxml"));
    return new Scene(root);
  }
}