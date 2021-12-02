package domain.controllers;

import domain.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class CompetitionController extends Controller{
    private ArrayList<Competition> competitions = new ArrayList<>();
    public CompetitionController() {
        competitions = db.getCompetitions();

        try {
            db.loadCompetitions();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        competitions = db.getCompetitions();

    }

    public String competitionsToSTring(){
        String result = "";
        for (Competition competition : competitions) {
            ArrayList<CompetitionMemberResult> butterFlyResults = competition.getAllButterflyTimes();
            ArrayList<CompetitionMemberResult> crawlResults = competition.getALlCrawlTimes();
            ArrayList<CompetitionMemberResult> backStrokeResults = competition.getALlBackStrokeTimes();
            ArrayList<CompetitionMemberResult> breastStrokeResults = competition.getAllBreastStrokeTimes();
            Collections.sort(butterFlyResults);
            Collections.sort(crawlResults);
            Collections.sort(backStrokeResults);
            Collections.sort(breastStrokeResults);


            result += competition.getName();
            result += "\n\n";

            String butterFlyJunior = "";
            String butterFlySenior = "";
            for (CompetitionMemberResult butterFlyResult : butterFlyResults){
                Member member = db.getMemberById(butterFlyResult.getMemberId());
                String time = butterFlyResult.getResultTime();
                CompetitionMember competitionMember = ((CompetitionMember) member );
                if(competitionMember.getAge() < 18) {
                    butterFlyJunior += "\t" + competitionMember.getName() + " Tid: " + time + "\n";
                }else {
                    butterFlySenior += "\t" + competitionMember.getName() + " Tid: " + time + "\n";
                }
            }
            String crawlJunior = "";
            String crawlSenior = "";
            for (CompetitionMemberResult crawlResult : crawlResults) {
                Member member = db.getMemberById(crawlResult.getMemberId());
                String time = crawlResult.getResultTime();
                CompetitionMember competitionMember = ((CompetitionMember) member );
                if(competitionMember.getAge() < 18) {
                    crawlJunior += "\t" + competitionMember.getName() + " Tid: " + time + "\n";
                }else {
                    crawlSenior += "\t" + competitionMember.getName() + " Tid: " + time + "\n";
                }
            }
            String backStrokeJunior = "";
            String backStrokeSenior = "";
            for (CompetitionMemberResult backStrokeResult : backStrokeResults) {
                Member member = db.getMemberById(backStrokeResult.getMemberId());
                String time = backStrokeResult.getResultTime();
                CompetitionMember competitionMember = ((CompetitionMember) member );
                if(competitionMember.getAge() < 18) {
                    crawlJunior += "\t" + competitionMember.getName() + " Tid: " + time + "\n";
                }else {
                    crawlSenior += "\t" + competitionMember.getName() + " Tid: " + time + "\n";
                }
            }

            String breastStrokeJunior = "";
            String breastStrokeSenior = "";
            for (CompetitionMemberResult breastStrokeResult : breastStrokeResults) {
                Member member = db.getMemberById(breastStrokeResult.getMemberId());
                String time = breastStrokeResult.getResultTime();
                CompetitionMember competitionMember = ((CompetitionMember) member );
                if(competitionMember.getAge() < 18) {
                    breastStrokeJunior += "\t" + competitionMember.getName() + " Tid: " + time + "\n";
                }else {
                    breastStrokeSenior += "\t" + competitionMember.getName() + " Tid: " + time + "\n";
                }
            }


            result += "Butterfly Resultater:\n";
            result += "Junior:\n";
            result += butterFlyJunior;
            result += "\nSenior:\n";
            result += butterFlySenior;
            result += "\nCrawl Resultater:\n";
            result += "Junior:\n";
            result += crawlJunior;
            result += "\nSenior:\n";
            result += crawlSenior;
            result += "\nRygcrawl Resultater:\n";
            result += "Junior:\n";
            result += backStrokeJunior;
            result += "\nSenior:\n";
            result += backStrokeSenior;
            result += "\nBrystsvømning Resultater:\n";
            result += "Junior:\n";
            result += breastStrokeJunior;
            result += "\nSenior:\n";
            result += breastStrokeSenior;
        }
        return result;
    }



}
