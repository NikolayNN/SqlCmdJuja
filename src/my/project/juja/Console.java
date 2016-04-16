package my.project.juja;

import java.util.Scanner;

/**
 * Created by Nikol on 4/12/2016.
 */
public class Console {
    public String readString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void writeStringln(String message){
        System.out.println(message);
    }

    public static void writeStringln(){
        System.out.println();
    }

    public static void writeString(String message){
        System.out.print(message);
    }

}
