package utility;

import pojos.Registrant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToTxt {

    public static void generateData(Registrant registrant, String fileName){

        try {

            FileWriter fileWriter = new FileWriter(fileName,true); // if  we make it "true" we can see al the records. Otherwise, the new records will be written instead of previous ones

            BufferedWriter writer = new BufferedWriter(fileWriter); // this create the records in the file

            writer.append(registrant.getFirstName()+ ","+ registrant.getLastName()+","+ registrant.getLogin()+","+registrant.getSsn()+"\n");

            writer.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
