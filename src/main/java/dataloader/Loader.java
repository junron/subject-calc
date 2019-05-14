package dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Loader {

  private static Subject load(File f) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(f, Subject.class);
  }

  public static Subject load(String filename) throws IOException {
    return load(new File("subject-data/"+filename));
  }

  public static ArrayList<Subject> loadAll() throws IOException {
    File dataDir = new File("subject-data");
    ArrayList<Subject> subjects = new ArrayList<>();
    for(File f : Objects.requireNonNull(dataDir.listFiles())){
      if(f.isFile()){
        subjects.add(load(f.getName()));
      }
    }
    return subjects;
  }

}
