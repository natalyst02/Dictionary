package display.graphic.intro;

import display.graphic.texttranslate.TextTranslateController;

import module.LevenshteinAlthogrim;
import module.TextToSpeech;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dictionary.DictionaryManagement;
import display.graphic.addword.AddController;
import display.graphic.favoriteword.FavoriteController;
import display.graphic.editword.EditController;
import display.dialog.DialogConfirm;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import utility.ConfigurationProject;
import utility.UtilsProject;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IntroController implements Initializable {

  protected DictionaryManagement OurDictionary = DictionaryManagement.getDictionaryManagement();

  @FXML
  public TextField TextFieldSearch;

  @FXML
  private WebView ViewWordExplain;

  @FXML
  private FontAwesomeIconView SpeakerButtonn;

  @FXML
  private ListView<String> listView;

  @FXML
  private VBox SceneWordExplain;

  @FXML
  private ToolBar toolBar;

  @FXML
  private BorderPane borderPane;

  @FXML
  private FontAwesomeIconView favorite;

  public static String directWord;

  @FXML

  public void searchWord() {
    directWord = TextFieldSearch.getText();
    if (OurDictionary.checkbeContained(directWord)) {
      setSceneExplain();
    } else {
      setthesameMean();
    }
  }

  public void changeFavorSituation() {
    if (favorite.getGlyphName().equals("HEART")) {
      favorite.setGlyphName("HEART_ALT");
      OurDictionary.setFavorSituation(directWord, 0);
    } else {
      favorite.setGlyphName("HEART");
      OurDictionary.setFavorSituation(directWord, 1);
    }
  }

  public void pronounceWord() {
    TextToSpeech.speak(directWord);
  }

  public void setSceneAdd() throws IOException {
    ConfigurationProject.introStage.setScene(AddController.showScene());
  }

  public void setSceneEdit() throws IOException {
    ConfigurationProject.introStage.setScene(EditController.showScene());
  }

  public void setGGTransText() throws IOException {
    ConfigurationProject.introStage.setScene(TextTranslateController.showScene());
  }

  public void setSceneFavor() throws IOException {
    ConfigurationProject.introStage.setScene(FavoriteController.showScene());
  }

  public void deleteWord() throws SQLException {
    DialogConfirm deleteNoti = new DialogConfirm();
    boolean checkNoti = deleteNoti.show("Delete", "Do you want to delete this word?");
    if (checkNoti) {
      ViewWordExplain.getEngine()
          .loadContent("<h1>Không tìm thấy dữ liệu.</h1>");
      OurDictionary.deleteWord(directWord);
      showSuggestedWords();
    }
  }

  public void showSuggestedWords() throws SQLException {
    listView.getItems().clear();
    ResultSet resSet = OurDictionary.SearchDic(TextFieldSearch.getText());
    while (resSet.next()) {
      listView.getItems().add(resSet.getString("word"));
    }
  }

  public void getSelectedWordFromSuggestedList() {
    ObservableList<String> IndiciesSelected = listView.getSelectionModel().getSelectedItems();
    TextFieldSearch.setText(IndiciesSelected.get(0));
    searchWord();
  }


  public void setSceneExplain() {
    String htmlOfSearchedWord = OurDictionary.LookupDic(directWord);
    htmlOfSearchedWord =
        "<body style=" + "\"background-color:#FFFFFFFF;" + "\">" + htmlOfSearchedWord + "</body>";
    int checkbeFavorited = OurDictionary.checkbeFavorited(directWord);
    htmlOfSearchedWord = UtilsProject.setUneditable(htmlOfSearchedWord);
    ViewWordExplain.getEngine().loadContent(htmlOfSearchedWord);
    if (checkbeFavorited == 0) {
      favorite.setGlyphName("HEART_ALT");
    } else {
      favorite.setGlyphName("HEART");
    }
    SpeakerButtonn.setVisible(true);
    favorite.setVisible(true);
    borderPane.setCenter(SceneWordExplain);
  }

  private void setthesameMean() {
    StringBuilder html = new StringBuilder("<h1>Không tìm thấy dữ liệu.</h1>");
    html.append("<h1>Có phải từ bạn muốn tìm kiếm là: </h1>");
    String[] res = LevenshteinAlthogrim.getMostSimilar(directWord);
    for (String word : res) {
      if (word == null) {
        continue;
      }
      html.append("<h1>").append(word).append("</h1>");
    }
    html = new StringBuilder(
        "<body style=" + "\"background-color:#FFFFFFFF;" + "\">" + html + "</body>");
    ViewWordExplain.getEngine().loadContent(html.toString());
    borderPane.setCenter(SceneWordExplain);
  }

  public static Scene showScene() throws IOException {
    Parent root = FXMLLoader.load(IntroController.class.getResource("Intro.fxml"));
    return new Scene(root);
  }

  public void VerEngVie() throws IOException {
    ConfigurationProject.TypeDb = "av";
    ConfigurationProject.introStage.setScene(IntroController.showScene());
  }

  public void VietAnhVersion() throws IOException {
    ConfigurationProject.TypeDb = "va";
    ConfigurationProject.introStage.setScene(IntroController.showScene());
  }

  public void About(ActionEvent event)//hàm thông tin
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(
        "This Dictionary was written by Nguyen Anh Tuan & Do Thuy Linh & Le Thai Son");
    alert.show();
  }

  public void Quit(ActionEvent event) {//ham đóng ứng dụng
    Platform.exit();
    System.exit(0);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      showSuggestedWords();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (directWord == null) {
    } else {
      setSceneExplain();
    }
  }
}