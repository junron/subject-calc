package application;

import dataloader.Assessment;
import dataloader.Loader;
import dataloader.Subject;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;

public class SubjectCalcController {


  @FXML
  private GridPane gridPane;

  @FXML
  private Label totalOut;

  @FXML
  private Label gradeOut;


  private Subject subject;

  @FXML
  private void calculate() {
    double total = subject.getTotalScore();
    totalOut.setText(String.format("Total: %.2f%%", total));
    gradeOut.setText(String.format("Grade: %s", getGrade(total / 100)));
    gradeOut.setTextFill(Color.web(getGradeColor(total / 100)));
  }

  @FXML
  public void initialize() {
//    gridPane.setGridLinesVisible(true);
    try {
      subject = Loader.load("cs.json");
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    ArrayList<Assessment> assessments = subject.getAssessments();
    ColumnConstraints c = new ColumnConstraints();
    c.setPercentWidth(100.0 / (assessments.size() + 1));
    int i = 1;
    double totalWeightage = 0;
    for (Assessment a : assessments) {
      gridPane.getColumnConstraints().add(c);

//    Assessment names
      Label name = new Label(a.getName());
      name.setFont(new Font(16));
      gridPane.add(name, i, 0);
      GridPane.setHalignment(name, HPos.CENTER);

//    Assessment scores
      Label maxScore = new Label("/" + a.getMaxScore());
      maxScore.setFont(new Font(14));
      maxScore.setPadding(new Insets(0, 5, 0, 0));
      gridPane.add(maxScore, i, 1);
      GridPane.setHalignment(maxScore, HPos.RIGHT);

//    Assessment score text fill
      TextField tf = new TextField();
      tf.textProperty().addListener(((observable, oldValue, newValue) -> {
        double score = getScores(tf.getText(), a.getMaxScore());
        if (score < 0) {
          return;
        }
        a.setScore(score);
        name.setTextFill(Color.web(a.getGradeColor()));
        calculate();
      }));
      tf.setMaxWidth(40);
      gridPane.add(tf, i, 1);
      GridPane.setHalignment(tf, HPos.LEFT);

//    Assessment weightage
      Label weightage = new Label(a.getWeightage() + "%");
      weightage.setFont(new Font(14));
      gridPane.add(weightage, i, 2);
      GridPane.setHalignment(weightage, HPos.CENTER);
      totalWeightage+=a.getWeightage();
      i++;
    }
    if(Math.round(totalWeightage)!=100){
      Alert a = new Alert(Alert.AlertType.WARNING);
      a.setHeaderText("Total weightage does not add up to 100%!");
      a.showAndWait();
    }
  }

  //  Grade is a double from 0-1
  private String getGrade(double d) {
    if (Math.round(d * 100) >= 85) {
      return "A+";
    }
    if ((d * 100) < 40) {
      return "F";
    }
    d = Math.round(d * 100) - 40;
    String[] grades = new String[]{"A", "A-", "B+", "B", "B-", "C+", "C", "D+", "D", "F"};
    int index = (int) (d / 5) + 1;
    return grades[(grades.length - index) - 1];
  }

  private String getGradeColor(double d) {
    if (Math.round(d * 100) >= 85) {
      return "#85bb5c";
    }
    if ((d * 100) < 40) {
      return "#bf360c";
    }
    d = Math.round(d * 100) - 40;
    String[] grades = new String[]{"#85bb5c", "#9ccc65", "#d2ce56", "#ffa000", "#f57c00", "#ef6c00", "#ff5722", "#e64a19", "#d84315", "#bf360c"};
    int index = (int) (d / 5) + 1;
    return grades[(grades.length - index) - 1];
  }

  private double getScores(String input, double limit) {
    if (input.equals("")) {
      return 0;
    }
    try {
      double score = Double.parseDouble(input);
      if (score < 0 || score > limit) {
        throw new NumberFormatException();
      }
      return score;
    } catch (NumberFormatException e) {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setHeaderText("Invalid score!!");
      a.showAndWait();
      return -1;
    }
  }

}
