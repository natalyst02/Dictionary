package display.graphic.favoriteword;


import display.dialog.DialogConfirm;
import display.graphic.intro.IntroController;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import utility.ConfigurationProject;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXListView;

public class FavoriteController extends IntroController implements Initializable {

  @FXML
  JFXListView<String> listFavorWord;

  public void getFavors() {
    listFavorWord.getItems().clear();
    ResultSet resSet = OurDictionary.getFavor();
    while (true) {
      try {
        if (!resSet.next()) break;
        listFavorWord.getItems().add(resSet.getString("word"));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void getFavorWordinList() throws IOException {
    ObservableList<String> IndiciesSelected = listFavorWord.getSelectionModel().getSelectedItems();
    directWord = IndiciesSelected.get(0);
    ConfigurationProject.introStage.setScene(IntroController.showScene());
  }

  public static Scene showScene() throws IOException {
    Parent root = FXMLLoader.load(FavoriteController.class.getResource("Favorite.fxml"));
    return new Scene(root);
  }

  public void setButtonBack() throws IOException {
    DialogConfirm cancelNotification = new DialogConfirm();
    boolean checkNoti = cancelNotification.show("Favorite Word",
        "Do you want to back?");
    if (checkNoti) {
      ConfigurationProject.introStage.setScene(IntroController.showScene());
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    getFavors();
  }
}