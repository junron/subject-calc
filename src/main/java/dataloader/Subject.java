package dataloader;

import java.util.ArrayList;

public class Subject extends JsonToString {
  private String name;
  private ArrayList<Assessment> assessments;

  public String getName() {
    return name;
  }

  public double getTotalScore() {
    double res = 0;
    for (Assessment a : assessments) {
      res+=a.getOverallScore();
    }
    return res;
  }

  public ArrayList<Assessment> getAssessments() {
    return assessments;
  }
}
