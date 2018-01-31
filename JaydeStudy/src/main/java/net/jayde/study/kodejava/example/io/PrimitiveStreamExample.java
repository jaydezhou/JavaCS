package net.jayde.study.kodejava.example.io;

import java.io.*;

public class PrimitiveStreamExample {

    public static void main(String[] args) {
        //
        // Prepares some data to be written to a file.
        //
        int cityIdA = 1;
        String cityNameA = "Green Lake City";
        int cityPopulationA = 500000;
        float cityTempA = 15.50f;

        int cityIdB = 2;
        String cityNameB = "Salt Lake City";
        int cityPopulationB = 250000;
        float cityTempB = 10.45f;

        try {
            //
            // Create an instance of FileOutputStream with cities.dat
            // as the file name to be created. Then we pass the input
            // stream object in the DataOutputStream constructor.
            //
            FileOutputStream fos = new FileOutputStream("cities.dat");
            DataOutputStream dos = new DataOutputStream(fos);

            //
            // Below we write some data to the cities.dat.
            // DataOutputStream class have various method that allow
            // us to write primitive type data and string. There are
            // method called writeInt(), writeFloat(), writeUTF(),
            // etc.
            //
            dos.writeInt(cityIdA);
            dos.writeUTF(cityNameA);
            dos.writeInt(cityPopulationA);
            dos.writeFloat(cityTempA);

            dos.writeInt(cityIdB);
            dos.writeUTF(cityNameB);
            dos.writeInt(cityPopulationB);
            dos.writeFloat(cityTempB);

            dos.flush();
            dos.close();

            //
            // Now we have a cities.dat file with some data in it.
            // Next you'll see how easily we can read back this
            // data and display it. Just like the DataOutputStream
            // the DataInputStream class have the corresponding
            // read methods to read data from the file. Some of
            // the method names are readInt(), readFloat(),
            // readUTF(), etc.
            //
            FileInputStream fis = new FileInputStream("cities.dat");
            DataInputStream dis = new DataInputStream(fis);

            //
            // Read the first data
            //
            int cityId1 = dis.readInt();
            System.out.println("Id: " + cityId1);
            String cityName1 = dis.readUTF();
            System.out.println("Name: " + cityName1);
            int cityPopulation1 = dis.readInt();
            System.out.println("Population: " + cityPopulation1);
            float cityTemperature1 = dis.readFloat();
            System.out.println("Temperature: " + cityTemperature1);

            //
            // Read the second data
            //
            int cityId2 = dis.readInt();
            System.out.println("Id: " + cityId2);
            String cityName2 = dis.readUTF();
            System.out.println("Name: " + cityName2);
            int cityPopulation2 = dis.readInt();
            System.out.println("Population: " + cityPopulation2);
            float cityTemperature2 = dis.readFloat();
            System.out.println("Temperature: " + cityTemperature2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
