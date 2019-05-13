package dataloader;

public class Assessment extends JsonToString {
  private String name;
  private double weightage;
  private double maxScore;
  private double score;


  public String getName() {
    return name;
  }

  public double getWeightage() {
    return weightage;
  }

  public double getMaxScore() {
    return maxScore;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  double getOverallScore(){
    return (score/maxScore)*weightage;
  }

  public String getGradeColor() {
    double d = score/maxScore;
    if (Math.round(d * 100) >= 85) {
      return "#85bb5c";
    }
    if ((d * 100)<40){
      return "#bf360c";
    }
    d = Math.round(d * 100) - 40;
    String[] grades = new String[]{"#85bb5c", "#9ccc65", "#d2ce56", "#ffa000", "#f57c00", "#ef6c00", "#ff5722", "#e64a19", "#d84315", "#bf360c"};
    int index = (int) (d / 5) + 1;
    return grades[(grades.length - index) - 1];
  }

}
