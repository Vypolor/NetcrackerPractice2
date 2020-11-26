package buildings;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class lab9 {

    static File filename;

    static JFileChooser fileChooser = new JFileChooser();

    public static void main(String[] args) {

        JFrame mainWindow = mainFrame();
        JPanel jPanel = new JPanel();

        setMenu(mainWindow, jPanel);


    }


    static JFrame mainFrame() {
        JFrame jFrame = new JFrame() {};
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setBounds(600, 200, 750, 750);

        jFrame.setTitle("Lab - 9");

        return jFrame;
    }




    static void setMenu(JFrame jFrame, JPanel jPanel){
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        menuBar.add(file);

        file.add(new JMenuItem("Open dwelling")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOpenFile(jPanel);
                filename = fileChooser.getSelectedFile();
                Buildings.setBuildingFactory(new DwellingFactory());
            }
        });
        file.addSeparator();
        file.add(new JMenuItem("Open office building")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOpenFile(jPanel);
                filename = fileChooser.getSelectedFile();
                Buildings.setBuildingFactory(new OfficeFactory());
            }
        });
        file.addSeparator();
        file.add(new JMenuItem("Open hotel")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOpenFile(jPanel);
                filename = fileChooser.getSelectedFile();
                Buildings.setBuildingFactory(new HotelFactory());
            }
        });
        file.addSeparator();
        file.add(new JMenuItem("Exit")).addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }));


        jFrame.setJMenuBar(menuBar);
        jFrame.revalidate();
    }




    static void addOpenFile(JPanel jPanel){
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.getName().endsWith("txt")){
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "TXT Files only";
            }
        });

        fileChooser.showOpenDialog(jPanel);
    }

    static Building createBuilding(BuildingFactory build){
        Buildings.setBuildingFactory(build);
        Building building = null;
        //File file = fileChooser.getSelectedFile();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            building = Buildings.readBuilding(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return building;
    }
}




