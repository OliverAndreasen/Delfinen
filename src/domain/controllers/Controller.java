package domain.controllers;

import database.Database;
import domain.Accounting;
import domain.CompetitionMember;
import domain.Member;
import ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private UserInterface ui = new UserInterface();
    private Database db = new Database();
    private boolean programIsRunning = true;
    private Accounting accountant = new Accounting();

    public void start() {
        ui.start();
        // Fetch members from database
        while (programIsRunning) {
            switch (ui.userInputNumber()) {
                case 1 -> {
                    ui.printChairmanMenu();
                    ChairmanController chairmanController = new ChairmanController(ui, db, programIsRunning);
                    chairmanController.start();
                }

                case 2 -> {
                    ui.printAccountantMenu();
                    AccountantController accountantController = new AccountantController(ui, db, accountant, programIsRunning);
                    accountantController.start();
                }

                case 3 -> {
                    ui.printMemberMenu();
                    MemberController memberController = new MemberController(ui, db, programIsRunning);
                    memberController.start();
                }

                case 4 -> {
                    ui.printCoachMenu();
                    CoachController coachController = new CoachController(ui, db, programIsRunning);
                    coachController.start();
                }
            }
        }
    }


    public void test() {
        for (Member member : db.getAllMembers()) {
            if (member instanceof CompetitionMember) {
                if (((CompetitionMember) member).getBestTrainingTimeDates() != null) {
                    System.out.println(Arrays.toString(((CompetitionMember) member).bestTrainingTimeDatesToString()));
                    for (int i = 0; i < ((CompetitionMember) member).bestTrainingTimeDatesToString().length; i++) {
                        System.out.println(((CompetitionMember) member).getDateById(i));

                    }
                }
            } else {
                System.out.println(member);
            }
        }
    }

}

