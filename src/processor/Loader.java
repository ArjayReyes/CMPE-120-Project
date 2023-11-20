package processor;

import java.io.*;
import processor.Memory;

public class Loader {
  private Memory memory;

  public Loader(Memory memory) {
    this.memory = memory;
  }

  public void loadInstructions(String filename) throws IOException {

    FileInputStream fis = new FileInputStream(filename);
    DataInputStream dis = new DataInputStream(fis);

    String address = "00000000000000000000000000000000";

    while(dis.available() > 0) {
     
      String instruction = "";

      for(int i=0; i<4; i++) {
        String line = dis.readLine();
        instruction = line + instruction; 
      }

      memory.setMemoryValue(address, instruction);
      address = Utility.StringCrement(address, 1);
    }

    dis.close();
    fis.close();
  }
}
