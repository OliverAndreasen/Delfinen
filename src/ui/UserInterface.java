package ui;

import domain.Member;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public String userInput() {
        return scanner.nextLine();
    }

    public int userInputNumber() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public void start() {
        System.out.println("""
                Login som:
                    1. Formand
                    2. Kasserer
                    3. Medlemmer
                    4. Træner
                """);
    }

    public void printCoachMenu() {
        System.out.println("""
                1. Tilføj konkurrencesvømmer til hold.
                2. Tilføj konkurrencesvømmer bedste tid.
                3. Vis top 5.
                """);
    }

    public void printChoseAgeGroup() {
        System.out.println("""
                vælg alders gruppe:
                    1. Junior
                    2. Senior
                    """);
    }

    public void printTop5Lists() {
        System.out.println("""
                Vælg en disciplin:
                    1. Butterfly.
                    2. Crawl.
                    3. Rygcrawl.
                    4. Brystsvømning.
                """);
    }

    public void printMemberMenu() {
        System.out.println("""
                1. Betal kontingent.
                2. Skift medlemsstatus.
                3. Afslut medlemskab.
                4. Tilmeld konkurrence.
                """);
    }

    public void printAccountantMenu() {
        System.out.println("""
                1. Tjek forventet kontingent indkomst.
                2. Tjek medlemmer i restance.
                3. Tilføj restance til medlem.
                """);
    }

    public void printMembersWithDebt(String membersWithDebt) {
        System.out.println(membersWithDebt);
    }

    public void printDebtList(ArrayList<Member> membersWithDebt) {
        for (Member member : membersWithDebt) {
            System.out.println(member);
        }
    }

    public void printChangeActiveStatus() {
        System.out.println("Skift mellem aktiv og passiv kontigent.");
    }

    public void addMemberIdToDebtPrint() {
        System.out.print("Hvilket medlem skal tilføjes til restance?: ");
    }

    public void memberAddedToDebtPrint(String name) {
        System.out.printf("\t%s added to list of members with debt.\n", name);
    }


    public void printTotalSubscription(int total) {
        System.out.printf("Den forventede indkomst af alle kontingenter er: %d.-", total);
    }


    public void printChairmanMenu() {
        System.out.println("""
                1. Tilføj nyt medlem.
                2. Slet medlem.
                """);
    }

    public void printString(String str) {
        System.out.print(str);
    }


    public void printChairManAddMember() {
        System.out.println("Tilføj nyt medlem.");
    }

    public void printChairManDeleteMember() {
        System.out.print("Slet medlem på medlemsID: ");
    }

    public void printMemberDeleted(String name) {
        System.out.printf("%s slettet ", name);
    }

    public void chooseTeamType() {
        System.out.println("""
                \tVælg hold:
                    1. Junior
                    2. Senior
                    3. Motionist
                    4. Konkurrencesvømmer
                """);
    }

    public void printErrorMessage() {
        System.out.println("Noget gik galt.");
    }


}




