import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideBasics2Test {


    @Test
    public void books(){
        Configuration.assertionMode = SOFT;
        Configuration.startMaximized = true;

        //Navigate to the https://demoqa.com/books
        open("https://demoqa.com/books");

        //Using inner elements locators chain Find all books with publisher 'O'Reilly Media' containing title 'Javascript'
        System.out.println($$("div").filterBy(cssClass("rt-tr-group")).filterBy(text("O'Reilly Media")).filterBy(text("javascript")).texts());


        // Check with selenide soft assertions that size of returned list equals to 10(failed case)
        ElementsCollection books = $$("div").filterBy(cssClass("rt-tr-group")).filterBy(text("O'Reilly Media")).filterBy(text("javascript"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(books.size(),size(10),"Failed Task");



        //Using inner elements locators chain Find that each books images are not empty(success case)
        System.out.println("");

        int expectedResult = 8;
        ElementsCollection images = $("div.rt-tbody").findAll("img");
        softAssert.assertEquals(images.size(),expectedResult);

        //ასერტითაც ვაკეთებ და ვპრინტავ კიდეც ზომას
        System.out.println("Images are not empty");
        System.out.println("Number of images : " + images.size());

        softAssert.assertAll();



    }
}
