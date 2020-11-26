package buildings.net.client;

import buildings.Building;
import buildings.Buildings;
import buildings.DwellingFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BinaryClient {
    public static void main(String[] args) {

        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                DataOutputStream dos =
                        new DataOutputStream(
                                socket.getOutputStream());

                DataInputStream dis =
                        new DataInputStream(
                                socket.getInputStream());
        ){

            String type;
            Building building;
            String result;
            String path = "D:/NetcrackerTxt/task8/result.txt";
            FileWriter writer = new FileWriter(new File(path));
            Scanner scanner = new Scanner(new FileReader("D:/NetcrackerTxt/task8/BuildingType.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("D:/NetcrackerTxt/task8/BuildingsInfo.txt"));
            Thread.sleep(5000);
            while(scanner.hasNext()){

                type = scanner.nextLine();
                System.out.println(type);
                Thread.sleep(1000);
                dos.writeUTF(type);
                building = Buildings.readBuilding(reader);
                Thread.sleep(1000);
                Buildings.outputBuilding(building, dos);
                result = dis.readUTF();
                //Thread.sleep(1000);
                writer.write(result + "\n");
                System.out.println(result);
                //totalRes += result + " ";
            }

            writer.close();
            scanner.close();
            reader.close();
            dos.writeUTF("Exit");
            System.out.println("Exit");
            //System.out.println(totalRes);
            dos.flush();
            //scanner.close();
            //reader.close();

            //System.out.println(results);
            //StringTokenizer tokenizer = new StringTokenizer()
            //StringTokenizer tokenizer = new StringTokenizer()
            //byte[] arr = dis.readAllBytes();
            //String str = new String(arr);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        //=======================================================================================


        //BufferedReader BuildingInfo = new BufferedReader(new FileReader("E:/Kreker/#8/BuildingInfo.txt"));

        /*File file = new File("E:/Kreker/#8", "BuildingPrice.txt");
        file.createNewFile();

        Scanner scanner = new Scanner(new File("E:/Kreker/#8/BuildingTypes.txt"));
        Reader reader = new FileReader("E:/Kreker/#8/BuildingInfo.txt");
        String type;
        Building building;
        double price;
        while(scanner.hasNext()){
            type = scanner.nextLine();
            switch (type){
                case "Dwelling":
                    Buildings.setBuildingFactory(new DwellingFactory());
                    building = Buildings.readBuilding(reader);
                    price = building.getGenSquare()*1000;
                    break;
            }

        }*/


    }
}
