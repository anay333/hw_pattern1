package ru.netology;

import com.codeborne.selenide.Selectors;
import com.github.javafaker.Faker;
import data.DataGenerator;
import info.RegistrationInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Delivery {

    @Test
    void shouldSendRequest() {
         RegistrationInfo info = DataGenerator
                .Registration
                        .generateInfo("ru");


        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(info.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(String.valueOf(info.getDateDelivery()));
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $("[data-test-id='agreement']").click();
        $(Selectors.withText("Запланировать")).click();
        $("[class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + info.getDateDelivery()));

    }


}
