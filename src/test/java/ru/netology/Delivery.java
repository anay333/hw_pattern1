package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;



public class Delivery {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getFirstName()+" "+validUser.getLastName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
       $(Selectors.withText("Запланировать")).click();
       $("[class='notification__content']").shouldBe(Condition.visible)
               .$(Selectors.withText("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id='city'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $("[data-test-id='name'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='name'] input").setValue(validUser.getFirstName()+" "+validUser.getLastName());
        $("[data-test-id='phone'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $(Selectors.withText("Запланировать")).click();
        $("div.notification__content > button").click();
        $("[class='notification__content']").shouldBe(Condition.appear)
               .$(Selectors.byText("Встреча успешно запланирована на " + secondMeetingDate));

    }

    }




