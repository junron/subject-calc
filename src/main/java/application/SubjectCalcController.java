package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SubjectCalcController {

  private int paMax = 50;
  private int t1Max = 40;
  private int t2Max = 35;
  private int ge1Max = 45;
  private int ge2Max = 55;
  private int pa2Max = 52;

  @FXML
  private TextField pa;

  @FXML
  private TextField t1;

  @FXML
  private TextField t2;

  @FXML
  private TextField g1;

  @FXML
  private TextField g2;

  @FXML
  private Label totalOut;

  @FXML
  private Label gradeOut;
  @FXML
  private TextField pa2;

  @FXML
  void calculate(MouseEvent event) {
//    System.out.println(getGrade(0.849));
    try {
      double paVal = getScores(pa, paMax);
      double pa2Val = getScores(pa2, pa2Max);
      double t1Val = getScores(t1, t1Max);
      double t2Val = getScores(t2, t2Max);
      double ge1Val = getScores(g1, ge1Max);
      double ge2Val = getScores(g2, ge2Max);
      double total = (paVal+pa2Val) * 17.5 + (t1Val+t2Val) * 22.5 + (ge1Val+ge2Val) * 10.0;
      totalOut.setText(String.format("Total: %.2f%%",total));
      gradeOut.setText(String.format("Grade: %s",getGrade(total/100)));
      gradeOut.setTextFill(Color.web(getGradeColor(total/100)));
    } catch (Exception ignored) {
    }


  }

//  Grade is a double from 0-1
  private String getGrade(double d){
    if(Math.round(d*100)>=85){
      return "A+";
    }
    d = Math.round(d*100)-40;
    String[] grades = new String[]{"A","A-","B+","B","B-","C+","C","D+","D","F"};
    int index = (int)(d/5) + 1;
    return grades[(grades.length-index)-1];
  }

  private String getGradeColor(double d){
    if(Math.round(d*100)>=85){
      return "#85bb5c";
    }
    d = Math.round(d*100)-40;
    String[] grades = new String[]{"#85bb5c","#9ccc65","#d2ce56","#ffa000","#f57c00","#ef6c00","#ff5722","#e64a19","#d84315","#bf360c"};
    int index = (int)(d/5) + 1;
    return grades[(grades.length-index)-1];
  }

  private double getScores(TextField tf, int limit) {
    try {
      double score = Double.parseDouble(tf.getText());
      if (score < 0 || score > limit) {
        throw new NumberFormatException();
      }
      return score / limit;
    } catch (NumberFormatException e) {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setHeaderText("Invalid score!!");
      a.showAndWait();
      throw e;
    }
  }

}
