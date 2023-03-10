/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package munch;

import java.io.IOException;

public class App {

    Network network = new Network();

    public static final String NETWORK_TO_FILE = "NetworkToFile";

    public void runByCommand(String command, String inputFile) throws Exception {
        NetworkReader.createFromStoichometricMatrix(inputFile, network);
        if(NETWORK_TO_FILE.equals(command)){
            network.generateGraphTextFile();
        }
        else throw new Exception("Input command is not valid");
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(new App().getGreeting());
        App app = new App();
        String command = args[0];
        String inputFile = args[1];
        app.runByCommand(command, inputFile);
    }
}
