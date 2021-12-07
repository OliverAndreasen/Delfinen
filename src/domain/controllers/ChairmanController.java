package domain.controllers;

import domain.CompetitionMember;
import domain.Member;
import java.io.IOException;

public class ChairmanController extends Controller {

    public void startChairman() {
        ui.printChairmanMenu();
        while (programIsRunning) {
            switch (ui.userInputNumber()) {
                case 1 -> {
                    ui.printChairManAddMember();
                    try {
                        addMember();
                        ui.printString("Medlem oprettet");
                    } catch (Exception e) {
                        ui.printErrorMessage();
                    }
                }
                case 2 -> {
                    ui.printChangeActiveStatus();
                    changeActiveStatus();

                }
                case 3 -> {
                    ui.printChairManDeleteMember();
                    try {
                        String nameToBeDeleted = getMemberByIdAndDelete();
                        ui.printMemberDeleted(nameToBeDeleted);
                    } catch (Exception e) {
                        ui.printErrorMessage();
                    }
                }
                case 0 -> {
                    start();
                }
                default -> {
                    int min = 0;
                    int max = 2;
                    ui.printInvalidNumber(min, max);
                }
            }
        }
    }

    private String getMemberByIdAndDelete() {
        int memberId = ui.userInputNumber();
        Member member = getMemberById(memberId);
        if (member != null) {
            deleteMember(member);
            accountant.subtractSubscriptionTotal(accountant.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()));
            return member.getName();
        }
        return null;
    }

    public void addMember() {
        Member member = createMember();
        try {
            db.saveMember(member);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Member createMember() {
        int nextId;
        ui.printString("Name: ");
        String name = ui.userInput();
        ui.printString("Age: ");
        int age = ui.userInputNumber();
        String teamType = "";
        ui.chooseTeamType();
        switch (ui.userInputNumber()) {
            case 1 -> teamType = "Junior";
            case 2 -> teamType = "Senior";
            case 3 -> teamType = "Motionist";
            case 4 -> teamType = "Konkurrencesvømmer";
        }
        // create regular or competition member
        if (teamType.equals("Konkurrencesvømmer")) {
            nextId = db.nextIdCompetitionMember();
            return new CompetitionMember(nextId, name, age, true, teamType, true);
        } else {
            nextId = db.nextIdMember();
            return new Member(nextId, name, age, true, teamType, true);
        }
    }

    public void deleteMember(Member member) {
        db.deleteMember(member);

    }

    private void changeActiveStatus() {
        int memberId = ui.userInputNumber();
        Member member = db.getMemberById(memberId);
        toggleMemberActiveStatus(member);
        db.overWriteAndSaveFile();
    }

    private void toggleMemberActiveStatus(Member member) {
        member.setActiveStatus(!member.getActiveStatus());
    }

    public Member getMemberById(int memberId) {
        for (Member member : db.getAllMembers()) {
            if (memberId == member.getMemberId()) {
                return member;
            }
        }
        return null;
    }
}
