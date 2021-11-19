package ui;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);

    public String userInput(){
        return scanner.nextLine();
    }

    public int userInputNumber(){
        return scanner.nextInt();
    }

}
