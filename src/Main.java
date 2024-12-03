import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import java.nio.file.Path;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.StandardOpenOption.CREATE;
public static ArrayList <String> screenList = new ArrayList<String>();
public void main() throws IOException {

    Scanner input = new Scanner(System.in);
    boolean loopState = true;
    JFileChooser chooser = new JFileChooser();
    File working_directory = new File(System.getProperty("user.dir"));
    chooser.setCurrentDirectory(working_directory);
    boolean flag = false;
    String choice;
    do {
        displayList();
        choice = SafeInput.getNonZeroLenString(input, "What do you want to do \n A – Add an item to the list\nD – Delete an item from the list\nM – Move an item \n" +
                "I – Insert an item into the list\n" + "O – Open a list file from disk\n" +
                "S – Save the current list file to disk\n" +
                "C – Clear removes all the elements from the current list\n" +
                "V - View the current list \n"
                + "Q - ");
        if (choice.equalsIgnoreCase("A")) {
            flag = false;
            addItem(SafeInput.getNonZeroLenString(input, "What do you want to add"));
            System.out.println(screenList);
            continue;
        } else if (choice.equalsIgnoreCase("D")) {
            if (screenList.size() > 0) {
                flag = false;
                for (int i = 0; i < screenList.size(); i++) {
                    System.out.printf(" %s , %d ", screenList.get(i), i);
                }
                deleteItem(SafeInput.getRangedInt(input, "what index do you want to delete", 0, screenList.size() - 1));
                System.out.println(screenList);
                continue;
            } else {
                System.out.println("No Items to remove");
                continue;
            }
        } else if (choice.equalsIgnoreCase("I")) {
            flag = false;
            if (!screenList.isEmpty()) {
                insertItem(SafeInput.getRangedInt(input, "what index do you want to insert the item", 0, screenList.size() - 1), SafeInput.getNonZeroLenString(input, "What do you want to add"));
            } else {
                insertItem(0, SafeInput.getNonZeroLenString(input, "What do you want to add"));
            }
        } else if (choice.equalsIgnoreCase("V")) {
            displayList();
        } else if (choice.equalsIgnoreCase("q")) {
            loopState = !SafeInput.getYNconfirm(input, "Do you really want to quit");
            if (!flag) {
                boolean save = SafeInput.getYNconfirm(input, "Do you want to save the list before you quit");
                if(save){
                    saveItem(working_directory,chooser,input);
                }
            }
        } else if (choice.equalsIgnoreCase("O")) {
            int line = 0;
            if(!flag){
                boolean save = SafeInput.getYNconfirm(input, "Do you want to save the list before you quit");
                if(save){
                    saveItem(working_directory,chooser,input);
                }
            }
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File selected_file = chooser.getSelectedFile();
            Path file = selected_file.toPath();
            InputStream in = new BufferedInputStream(newInputStream(file, CREATE));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String rec = "";
            screenList.clear();

            while (reader.ready()) {
                rec = reader.readLine();
                screenList.add(rec);
                line++;
            }
            reader.close();}
            System.out.println(screenList);
            if(line<0){
                System.out.println("EXIT TO SAVE, READ ANOTHER FILE OR ABANDON IT");}
        } else if (choice.equalsIgnoreCase("S")) {
            flag = true;
            saveItem(working_directory,chooser,input);
        }else if (choice.equalsIgnoreCase("c")) {
            boolean destroy = SafeInput.getYNconfirm(input, "Do you really want to clear list");
            if(destroy){

            }
    }}while(loopState);}
    public static void displayList(){
        for(int i =0; i<screenList.size();i++){
            System.out.print(screenList.get(i)+",");
        }
    }
    public static void addItem(String item){
        screenList.add(item);
    }
    public static void deleteItem(int index){
        screenList.remove(index);
    }
    public static void insertItem(int index, String item){
        screenList.add(index,item);
    }

    public static void saveItem(File working_directory,JFileChooser chooser,Scanner input) throws IOException {
        boolean create_new_file = SafeInput.getYNconfirm(input, "Do you want to create a new file?");
        if(!create_new_file){
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

                Path file_name= chooser.getSelectedFile().toPath();
                System.out.println(file_name);
                OutputStream out = new BufferedOutputStream(Files.newOutputStream(file_name, CREATE));
                BufferedWriter writer = new  BufferedWriter(new OutputStreamWriter(out));
                for(String add : screenList) {
                    writer.write(add, 0, add.length());
                    writer.newLine();
                }
            }}else{
            System.out.println("Did not find file, creating a new file");
            String name = SafeInput.getNonZeroLenString(input,"What do you want the name for the new file");
            Path file = Paths.get(working_directory.getPath() +"\\src\\"+name+".txt");
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new  BufferedWriter(new OutputStreamWriter(out));
            for(String add : screenList) {
                writer.write(add, 0, add.length());
                writer.newLine();
            }}

    }