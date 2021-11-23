package domain;

import database.Database;
import ui.UserInterface;

import java.util.Scanner;

public class Controller {
    private UserInterface ui = new UserInterface();
    private Database db = new Database();
    private boolean programIsRunning = true;

    Scanner scanner = new Scanner(System.in);
    public String userInput(){
        return scanner.nextLine();
    }

    public int userInputNumber(){
        return scanner.nextInt();
    }

    public void start(){
        ui.start();

        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    ui.printChairmanMenu();
                    chairManController();
                }

                case 2 -> {
                    ui.printAccountantMenu();
                    accountantController();
                }

                case 3 -> {
                    ui.printMemberMenu();
                    memberController();
                }

                case 4 -> {
                    // TODO
                    // competetition member?
                }

                case 5 -> {
                    ui.printCoachMenu();
                    coachController();
                }

            }
        }
    }

    public void chairManController() {

        while(programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // TODO Tilføj nyt medlem.
                }

                case 2 -> {
                    // TODO Skift medlems hold.
                }

                case 3 -> {
                    // TODO Skift medlemstype.
                }

                case 4 -> {
                    // TODO Slet medlem.
                }
            }
        }
    }

    public void memberController() {

        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // TODO Betal kontingent.
                }

                case 2 -> {
                    // TODO Skift medlemsstatus.
                }

                case 3 -> {
                    // TODO Afslut medlemsskab.
                }

                case 4 -> {
                    // TODO Tilmeld konkurrence.
                }

            }
        }
    }

    public void accountantController() {

        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // TODO Tjek forventet kontingent indkomst.
                }

                case 2 -> {
                    // TODO Tjek medlemmer i restance.
                }
            }
        }
    }


    public void coachController() {

        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // TODO Tilføj konkurrencesvømmer til hold.
                }

                case 2 -> {
                    // TODO Tilføj konkurrencesvømmer bedste tid.
                }

                case 3 -> {
                    // TODO Vis top 5.
                }
            }
        }

    }


    // can change member from competion member to member or the other way.
    public void changeMember(){}

}

