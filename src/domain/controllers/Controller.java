package domain.controllers;

import database.Database;
import domain.Accounting;
import domain.CompetitionMember;
import domain.Member;
import ui.UserInterface;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Controller {
    protected UserInterface ui = new UserInterface();
    protected Database db = new Database();
    protected boolean programIsRunning = true;
    protected Accounting accountant = new Accounting();

    public void start() {
        ui.start();

        CompetitionController competitionController = new CompetitionController();
        DisciplineTimes disciplineTimes = new DisciplineTimes();
        disciplineTimes.sortBestTimes();
        System.out.println(competitionController.competitionsToSTring());
        // Fetch members from database
        try {
            while (programIsRunning) {
                switch (ui.userInputNumber()) {
                    case 1 -> {
                        ChairmanController chairmanController = new ChairmanController();
                        chairmanController.startChairman();
                    }
                    case 2 -> {
                        AccountantController accountantController = new AccountantController();
                        accountantController.startAccounting();
                    }
                    case 3 -> {
                        MemberController memberController = new MemberController();
                        memberController.startMember();
                    }
                    case 4 -> {
                        CoachController coachController = new CoachController();
                        coachController.startChoach();
                    }
                    default -> {
                        int min = 1;
                        int max = 4;
                        ui.printInvalidNumber(min, max);
                    }
                }
            }
        } catch (InputMismatchException e){
            System.out.println("indtast en gyldig værdi");
        }
    }

    public void test() {
        for (Member member : db.getAllMembers()) {
            if (member instanceof CompetitionMember) {
                if (((CompetitionMember) member).getBestTrainingTimeDates() != null) {
                    System.out.println(Arrays.toString(((CompetitionMember) member).bestTrainingTimeDatesToString()));
                    for (int i = 0; i < ((CompetitionMember) member).bestTrainingTimeDatesToString().length; i++) {
                        System.out.println(((CompetitionMember) member).getBestTrainingDateById(i));
                    }
                }
            } else {
                System.out.println(member);
            }
        }
    }

}

