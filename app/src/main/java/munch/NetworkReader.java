package munch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NetworkReader {

    public static final String INPUT_DELIMITER = "\t";

    public static void createFromStoichometricMatrix(String file, Network network) throws IOException {
        try(BufferedReader reader =  new BufferedReader(new FileReader("file"))){
            String line = reader.readLine();
            String[] rxnIds = line.split(INPUT_DELIMITER);
            for(int i = 1; i < rxnIds.length; i++){
                String rxnId = rxnIds[i];
                network.addReaction(rxnId);
            }
            int rxnCounter = 0;
            int metCounter = 0;
            line = reader.readLine();
            while(line != null){
                String[] elements = line.split(INPUT_DELIMITER);
                String met = elements[0];
                network.addMetabolite(met);
                for(int j = 1; j < elements.length; j++){
                    Reaction rxn = network.getReaction(rxnCounter);
                    int stNum = Integer.parseInt(elements[j]);
                    if(stNum < 0) rxn.addSubstrate(met);
                    if(stNum > 0) rxn.addProduct(met);
                    rxnCounter++;
                }
                line = reader.readLine();
                metCounter++;
                rxnCounter = 0;
            }
        }
    }
}
