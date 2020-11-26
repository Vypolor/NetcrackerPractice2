package buildings.net.server.parallel;

import buildings.*;
import buildings.BuildingUnderArrestException;
import buildings.dwelling.hotel.Hotel;
import buildings.office.OfficeBuilding;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {
    public static double calculatePrice(Building building) throws BuildingUnderArrestException {

        if(arrestedBuilding(building)) throw new BuildingUnderArrestException();
        double squarePrice;
        double square = building.getGenSquare();
        double result;
        if (building instanceof Hotel){
            squarePrice = 2000;
        }
        else if (building instanceof OfficeBuilding){
            squarePrice = 1500;
        }else{
            squarePrice = 1000;
        }
        result = squarePrice*square;
        return result;
    }

    public static boolean arrestedBuilding(Building building){
        int chance = (int)(Math.random()*10);
        if(chance > 8){
            return true;
        }else return false;
    }

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(1234)){

            System.out.println("Server started!");

            while(true){
                try{
                    Socket socket = server.accept();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try(ServerSocket serverSocket = new ServerSocket(1234)){

                                System.out.println("Server started!");

                                while(true){

                                    Socket socket = serverSocket.accept();

                                    DataOutputStream dos = new DataOutputStream(
                                            socket.getOutputStream());

                                    DataInputStream dis = new DataInputStream(
                                            socket.getInputStream()
                                    );
                                    Double sum;
                                    Integer s;
                                    Building building;
                                    while ((s = dis.readInt()) == 1){
                                        building = Buildings.deserializeBuilding(dis);
                                        Object price = null;
                                        try {
                                            System.out.println("Request Building:"
                                                    + building);
                                            price = calculatePrice(building);
                                            System.out.println("========================");
                                            System.out.println("Price of Building: " + price);
                                            System.out.println("========================");
                                            new ObjectOutputStream(dos).writeObject(price);
                                        } catch (BuildingUnderArrestException e){
                                            new ObjectOutputStream(dos).writeObject(new BuildingUnderArrestException());
                                        }
                                        //System.out.println(building);
                                        //dos.flush();
                                    }
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            finally {
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
