package dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Loader {

  private static Subject load(File f) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(f, Subject.class);
  }

  public static Subject load(String filename) throws IOException {
    return load(new File("subject-data/"+filename));
  }


}
