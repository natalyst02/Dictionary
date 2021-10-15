package dictionary;

import utility.ConfigurationProject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DictionaryManagement extends Dictionary {

  private static DictionaryManagement DicManagement;

  private DictionaryManagement() {

  }

  public static DictionaryManagement getDictionaryManagement() {
    if (DicManagement == null) {
      DicManagement = new DictionaryManagement();
      DicManagement.loadDataFromDatabase();
    }
    return DicManagement;
  }

  public void loadDataFromDatabase() {
    System.out.println("Loading");
    DicSQLite.connectDatabase(ConfigurationProject.PathDb);
    System.out.println("Loaded!");
  }

  public String LookupDic(String word) {
    word = word.replaceAll("'", "''");
    String query =
        "SELECT * FROM " + ConfigurationProject.TypeDb + " WHERE word LIKE " + "'" + word
            + "'";
    ResultSet resSet = DicSQLite.executeQuery(query);
    try {
      return resSet.getString("html");
    } catch (SQLException e) {
      return "<h1>Không tìm thấy dữ liệu.</h1>";
    }
  }

  public ResultSet SearchDic(String word) {
    word = word.replaceAll("'", "''");
    String query =
        "SELECT * FROM " + ConfigurationProject.TypeDb + " WHERE word LIKE " + "'" + word
            + "%'";
    return DicSQLite.executeQuery(query);
  }

  public boolean saveWord(Word word) {
    if (checkbeContained(word.word)) {
      return false;
    }
    System.out.println(word.word);
    String query = "INSERT INTO " + ConfigurationProject.TypeDb + "(id, word, html, favorite)"
        + "VALUES(?,?,?, 0)";
    int NumRows = DicSQLite.getIDmax();
    try {
      PreparedStatement PreStatement;
      PreStatement = DicSQLite.connection.prepareStatement(query);
      PreStatement.setInt(1, NumRows + 1);
      PreStatement.setString(2, word.word);
      PreStatement.setString(3, word.html);
      PreStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  public void editWord(String word, String newHtml) {
    String query =
        "UPDATE " + ConfigurationProject.TypeDb + " SET html = ?" + " WHERE word = ?";
    try {
      PreparedStatement PreStatement;
      PreStatement = DicSQLite.connection.prepareStatement(query);
      PreStatement.setString(1, newHtml);
      PreStatement.setString(2, word);
      PreStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteWord(String word) {
    word = word.replaceAll("'", "''");
    String query =
        "DELETE FROM " + ConfigurationProject.TypeDb + " WHERE word LIKE " + "'" + word + "'";
    try {
      PreparedStatement PreStatement;
      PreStatement = DicSQLite.connection.prepareStatement(query);
      /*
      thực hiện truy vấn
       */
      PreStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int checkbeFavorited(String word) {
    word = word.replaceAll("'", "''");
    String query =
        "SELECT favorite FROM " + ConfigurationProject.TypeDb + " WHERE word LIKE " + "'"
            + word + "'";
    try {
      return DicSQLite.executeQuery(query).getInt("favorite");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }


  public void setFavorSituation(String word, int status) {
    word = word.replaceAll("'", "''");
    String query = "UPDATE " + ConfigurationProject.TypeDb + " SET favorite" + " = " + status
        + " WHERE word" + " IS " + "'" + word + "'";
    try {
      PreparedStatement PreStatement;
      PreStatement = DicSQLite.connection.prepareStatement(query);
      PreStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ResultSet getFavor() {
    String query = "SELECT word FROM " + ConfigurationProject.TypeDb + " WHERE favorite = 1";
    return DicSQLite.executeQuery(query);
  }

  public boolean checkbeContained(String word) {
    String res = LookupDic(word);
    return !res.equals("<h1>Không tìm thấy dữ liệu.</h1>");
  }

}
