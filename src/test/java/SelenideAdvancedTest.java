import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.apache.bcel.classfile.Utility;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAdvancedTest {


    public void task() {

        String id = "see-book-Learning JavaScript Design Patterns";

        for(int i = 1; i <= 3; i++ ) {

            $(By.id(id)).click();
            $(By.xpath(".//button[text()='Back To Book Store']")).click();
            if(i == 1){
                id = "see-book-Speaking JavaScript";
            }
            if(i==2){
                id = "see-book-Programming JavaScript Applications";
            }

        }
    }



    @Test
    public void books() {
        Configuration.assertionMode = SOFT;
        Configuration.startMaximized = true;




        //Navigate to the https://demoqa.com/books
        open("https://demoqa.com/books");



        //Using find() and findAll() methods Find all books with publisher 'O'Reilly Media' containing title 'Javascript'
        ElementsCollection elList =  $("div.rt-tbody").findAll("div[class = 'rt-tr-group']").filterBy(text("O'Reilly Media")).filterBy(text("javascript"));
        System.out.println(elList.texts());




        //Check with Test NG soft assertions that size of returned list equals to 10(failed case)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(elList.size(),size(10),"List size doesn't equals to 10");



        //Check that first match row's title equals to 'Learning JavaScript Design Patterns'(success case)
        ElementsCollection books = $("div.rt-tbody").findAll("div[class = 'rt-tr-group']").filterBy(text("O'Reilly Media")).filterBy(text("javascript"));
        books.stream().findFirst().get().getText();

        String expectedResult = "Learning JavaScript Design Patterns";
        softAssert.assertEquals(books, expectedResult);
        System.out.println("");
        System.out.println("First match row's title equals to 'Learning JavaScript Design Patterns'");



        //Using stream() click on all matching row's title

        elList.stream().forEach(
                    el-> {
                        task();
                    }
                    );


        softAssert.assertAll();

    }

}

