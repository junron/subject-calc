package application;

import dataloader.Loader;
import dataloader.Subject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

  private ArrayList<Subject> subjects;
  @FXML
  private TabPane tabPane;

  @FXML
  void initialize() throws IOException {
    subjects = Loader.loadAll();
    for(Subject s : subjects){

      Tab t = new Tab();
      t.setText(s.getName());
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/subject.fxml"));
      AnchorPane a = loader.load();
      SubjectCalcController c = loader.getController();
      c.initialize(s);
      t.setContent(a);
      tabPane.getTabs().add(t);
    }
  }
}
