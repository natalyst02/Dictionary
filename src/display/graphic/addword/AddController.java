package display.graphic.addword;

import display.dialog.DialogInfor;
import display.graphic.intro.IntroController;
import dictionary.Word;
import display.dialog.DialogConfirm;
import display.dialog.DialogError;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import utility.ConfigurationProject;
import utility.UtilsProject;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;


public class AddController extends IntroController {

  @FXML
  private HTMLEditor htmlEditor;

  public boolean addWord() {
    String html = htmlEditor.getHtmlText();
    String word = UtilsProject.getHTMLTextWord(html);
    Word newWord = new Word(word, html);
    return OurDictionary.saveWord(newWord);
  }

  public void setButtonSave() throws IOException {
    DialogConfirm addWordNoti = new DialogConfirm();
    boolean checkNoti = addWordNoti.show("Add New Word",
        "Do you want to add this word?");
    if (checkNoti) {
      boolean beSaved = addWord();
      if (beSaved) {
        DialogInfor addNoti = new DialogInfor();
        addNoti.show("Add New Word", "Added successfully");
        setintroStage();
      } else {
        DialogError addNoti = new DialogError();
        addNoti.show("Add New Word", "The word was saved");
      }
    }
  }

  public void setButtonBack() throws IOException {
    DialogConfirm cancelNotification = new DialogConfirm();
    boolean checkNoti = cancelNotification.show("Add New Word",
        "Do you want to back?");
    if (checkNoti) {
      setintroStage();
    }
  }

  public void setintroStage() throws IOException {
    ConfigurationProject.introStage.setScene(IntroController.showScene());
  }

  public static Scene showScene() throws IOException {
    Parent root = FXMLLoader.load(AddController.class.getResource("AddWord.fxml"));
    return new Scene(root);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }
}