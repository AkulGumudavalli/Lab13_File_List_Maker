import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt){
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }
    public static int getInt(Scanner pipe, String prompt){
        boolean gotValue = false;
        int retInt = 0;
        do{
            System.out.println("\n"+prompt+": ");
            if(pipe.hasNextInt()){
                retInt = pipe.nextInt();
                gotValue = true;
            }else{
                System.out.println("Wrong input type "+pipe.nextLine());
                pipe.nextLine();
            }
        }while(!gotValue);
        return retInt;
    }
    public static double getDouble(Scanner pipe, String prompt){
        boolean gotValue = false;
        double retDouble = 0;
        do{
            System.out.println("\n"+prompt+": ");
            if(pipe.hasNextDouble()){
                retDouble = pipe.nextDouble();
                gotValue = true;
            }else{
                System.out.println("Wrong input type: "+pipe.nextLine());
                pipe.nextLine();
            }
        }while(!gotValue);
        return retDouble;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high){
        boolean gotValue = false;
        int retDouble = 0;
        do{
            System.out.println("\n"+prompt+" between "+low+ " and "+high+": ");
            if(pipe.hasNextDouble()){
                retDouble = pipe.nextInt();
                if(low>retDouble || retDouble>high){
                    System.out.println("Please input a value between "+low+" and "+high+" you entered "+retDouble);
                    pipe.nextLine();
                }
                else{
                    gotValue = true;
                }
            }else{
                System.out.println("Wrong input type: "+pipe.nextLine());
                pipe.nextLine();
            }
        }while(!gotValue);
        return retDouble;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high){
        boolean gotValue = false;
        double retDouble = 0;
        do{
            System.out.println("\n"+prompt+" between "+low+ " and "+high+": ");
            if(pipe.hasNextDouble()){
                retDouble = pipe.nextDouble();
                if(low>retDouble || retDouble>high){
                    System.out.println("Please input a value between "+low+" and "+high+" you entered "+retDouble);
                    pipe.nextLine();
                }
                else{
                    gotValue = true;
                }
            }else{
                System.out.println("Wrong input type: "+pipe.nextLine());
                pipe.nextLine();
            }
        }while(!gotValue);
        return retDouble;
    }
    public static boolean getYNconfirm(Scanner pipe, String prompt){
        boolean retBoolean = false;
        boolean gotValue = false;
        do{
            System.out.println("\n"+prompt+" Please enter Y/N: ");
            if(pipe.hasNextLine()){
                String input = pipe.nextLine();
                if(input.equalsIgnoreCase("Y")){
                    retBoolean = true;
                    gotValue = true;
                }else if(input.equalsIgnoreCase("N")){
                    retBoolean = false;
                    gotValue = true;
                }else{
                    System.out.println("You inputted wrong things, only Y or N, you inputted" + input);
                }
            }else{
                System.out.println("Wrong input type: "+pipe.nextLine());
                pipe.nextLine();
            }
        }while(!gotValue);
        return retBoolean;
    }
    public static String getregExString(Scanner pipe, String prompt, String regExPattern){
        String retString = "";
        boolean gotValue = false;

        do{
            System.out.println(prompt + ": ");

            if(pipe.hasNextLine()){
                retString = pipe.nextLine();
                if(retString.matches(regExPattern)){
                    retString = retString;
                    gotValue = true;
                }
                else{
                    System.out.println("Invalid Input, you inputted "+retString);
                }
            }
            else{
                System.out.println("Invalid Input "+pipe.nextLine()+" Please re-input");
            }
        }while(!gotValue);
        return retString;
    }
    public static void prettyHeader(String message){
        int total_char = 60;
        int message_char = 60 - message.length();
        int left_padding = message_char/2;
        int right_padding = message_char - left_padding;
        for(int i = 0; i<=total_char;i++){
            System.out.print("*");
        }
        System.out.print("\n***");
        for (int i =0; i<=left_padding-4;i++){
            System.out.print(" ");
        }
        System.out.print(message);
        for (int i =0; i<=right_padding-3;i++){
            System.out.print(" ");
        }
        System.out.print("***\n");

        for(int i = 0; i<=total_char;i++){
            System.out.print("*");
        }

    }
}