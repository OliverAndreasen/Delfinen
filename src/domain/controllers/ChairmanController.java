package domain.controllers;

import database.Database;
import domain.CompetitionMember;
import domain.Member;
import ui.UserInterface;

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
                    ui.printChairManDeleteMember();
                    try {
                        String nameToBeDeleted = findAndDeleteMember();
                        ui.printMemberDeleted(nameToBeDeleted);
                    } catch (Exception e) {
                        ui.printErrorMessage();
                    }
                }
            }
        }
    }

    private String findAndDeleteMember() {
        int memberId = ui.userInputNumber();
        Member memberExists = checkIfMemberExists(memberId);
        if (memberExists != null) {
            deleteMember(memberExists);
            return memberExists.getName();
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

    public Member checkIfMemberExists(int memberId) {
        for (Member member : db.getAllMembers()) {
            if (memberId == member.getMemberId()) {
                return member;
            }
        }
        return null;
    }
}
