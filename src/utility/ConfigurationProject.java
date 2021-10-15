package utility;


import javafx.scene.image.Image;
import javafx.stage.Stage;
import display.dialog.DialogConfirm;

public class ConfigurationProject {
    public static String PathDicIcon = "./resource/logo/dictionary_icon.png";
    public static int numberSimilarWord = 5;
    public static String PathDb = "jdbc:sqlite:resource/database/dict_hh.db";
    public static String TypeDb = "av";


    public static Stage introStage = new Stage();
    static {
        introStage.setTitle("Dictionary");
        Image dictionary_icon = UtilsProject.loadImage(ConfigurationProject.PathDicIcon);
        introStage.getIcons().add(dictionary_icon);
        introStage.setOnCloseRequest(close -> {
            close.consume();
            DialogConfirm CloseNoticeConFirm = new DialogConfirm();
            boolean checkNoti = CloseNoticeConFirm.show("Close", "Do you want to exit?");
            if (checkNoti) {
                introStage.close();
            } else {
                introStage.show();
            }
        });
    }
}
