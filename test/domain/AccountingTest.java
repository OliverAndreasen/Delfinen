package domain;

import database.Database;
import database.FileHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountingTest {

    @Test
    public void testCalculateSenior(){
        //Arrange
        Database db = new Database();
        Accounting accounting = new Accounting();
        Member member = new Member(2, "Bo", 20, true, "Senior", true);

        //Act
        accounting.addSubscriptionTotal(accounting.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()));

        int expected = 1600;

        //Assert
        assertEquals(expected, accounting.getSubscriptionTotal());
    }

    @Test
    public void testCalculateJunior(){
        //Arrange
        Database db = new Database();
        Accounting accounting = new Accounting();
        Member member = new Member(1, "alex", 10, true, "Junior", true);

        //Act
        accounting.addSubscriptionTotal(accounting.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()));

        int expected = 1000;

        //Assert
        assertEquals(expected, accounting.getSubscriptionTotal());
    }

    @Test
    public void testCalculatePassivt(){
        //Arrange
        Database db = new Database();
        Accounting accounting = new Accounting();
        Member member = new Member(3, "Ole", 20, false, "Senior", true);

        //Act
        accounting.addSubscriptionTotal(accounting.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()));

        int expected = 500;

        //Assert
        assertEquals(expected, accounting.getSubscriptionTotal());
    }

    @Test
    public void testCalculateSeniorRabat(){
        //Arrange
        Database db = new Database();
        Accounting accounting = new Accounting();
        Member member = new Member(4, "Hanne", 70, true, "Senior", true);

        //Act
        accounting.addSubscriptionTotal(accounting.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()));

        int expected = 1200;

        //Assert
        assertEquals(expected, accounting.getSubscriptionTotal());
    }

    @Test
    public void testCalculateSubscriptionTotal(){
        //Arrange
        Database db = new Database();
        Accounting accounting = new Accounting();
        ArrayList<Member> members = new ArrayList<>();

        Member member1 = new Member(1, "alex", 10, true, "Junior", true);
        Member member2 = new Member(2, "Bo", 20, true, "Senior", true);
        Member member3 = new Member(3, "Ole", 20, false, "Senior", true);
        Member member4 = new Member(4, "Hanne", 70, true, "Senior", true);
        members.add(member1);
        members.add(member2);
        members.add(member3);
        members.add(member4);

        //Act
        for (Member member : members){
            accounting.addSubscriptionTotal(accounting.calculateSubscriptionFee(member.getActiveStatus(),member.getAge()));
        }

        int expected = 4300;

        //Assert
        assertEquals(expected, accounting.getSubscriptionTotal());
    }
}