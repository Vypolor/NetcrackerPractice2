package buildings.net.client;

import buildings.*;
import buildings.BuildingUnderArrestException;
import buildings.dwelling.Dwelling;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SerialClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 1234);
             DataOutputStream dos =
                     new DataOutputStream(
                             socket.getOutputStream());

             DataInputStream dis =
                     new DataInputStream(
                             socket.getInputStream());){

            String type;
            Building building;

            Scanner types = new Scanner(new FileReader("D:/NetcrackerTxt/task8/BuildingType.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("D:/NetcrackerTxt/task8/BuildingsInfo.txt"));
            String path = "D:/NetcrackerTxt/task8/resultSerial.txt";
            FileWriter writer = new FileWriter(new File(path));

            Object price;

            while (types.hasNext()){
                type = types.nextLine();
                System.out.println(type);
                switch (type){
                    case "Dwelling":
                        Buildings.setBuildingFactory(new DwellingFactory());
                        break;
                    case "OfficeBuilding":
                        Buildings.setBuildingFactory(new OfficeFactory());
                        break;
                    case "Hotel":
                        Buildings.setBuildingFactory(new HotelFactory());
                }
                dos.writeInt(1);
                building = Buildings.readBuilding(reader);
                System.out.println(building);
                Buildings.serializeBuilding(building, dos);

                price = new ObjectInputStream(dis).readObject();
                if(price instanceof Double){
                    System.out.println("=====================");
                    System.out.println(price);
                    System.out.println("=====================");
                    String s = "" + price;
                    writer.write(s+"\n");
                }
                if(price instanceof BuildingUnderArrestException){
                    System.out.println("=====================");
                    System.out.println("Building under arest!");
                    System.out.println("=====================");
                    writer.write("Building under arest!\n");
                }

            }
            dos.writeInt(0);
            writer.close();
            dos.flush();

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
