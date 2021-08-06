import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTests {

    @Test
    public void checkbox() {
        open("http://the-internet.herokuapp.com/checkboxes");

        $("input[type=checkbox]").click();
        System.out.println($("input[type=checkbox]").shouldHave(type("checkbox")));

        System.out.println($(byXpath("//*[@id='checkboxes']/input[2]")));

    }
    @Test
    public void dropdown(){
        open("http://the-internet.herokuapp.com/dropdown");

        System.out.println($(byXpath("//*[@id='dropdown']/option[1]")));

        $("#dropdown").selectOption("Option 2");
        System.out.println($("#dropdown").getSelectedOption().shouldHave(text("Option 2"), value("2")));

    }
    @Test
    public void textBox(){
        open("https://demoqa.com/text-box");

        $("#userName").click();
        $("#userName").sendKeys("Nika Jvelauri");

        $("#userEmail").click();
        $("#userEmail").sendKeys("nika.jvelauri.1@btu.edu.ge");

        $("#currentAddress").click();
        $("#currentAddress").sendKeys("Home");

        $("#permanentAddress").click();
        $("#permanentAddress").sendKeys("Georgia");

        $("#submit").click();

        $$(".mb-1").shouldHave(exactTexts("Name:Nika Jvelauri","Email:nika.jvelauri.1@btu.edu.ge","Current Address  :Home","Permananet Address :Georgia"));



    }
}

