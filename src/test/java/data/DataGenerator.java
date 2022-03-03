package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    public static LocalDate generateDate(int shift) {


              LocalDate date = LocalDate.now().plusDays(shift);
        return date;
    }

    public static String generateCity() {
        String[] city = {"Казань", "Москва", "Уфа", "Майкоп", "Горно-Алтайск", "Улан-Удэ", "Махачкала", "Магас",
                "Нальчик"};
        int index = new Random().nextInt(city.length);
        return city[index];
    }

    public static String generateFirstName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String firstName = faker.name().firstName();
        return firstName;
    }

    public static String generateLastName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String lastName = faker.name().lastName();
        return lastName;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }


        public static UserInfo generateUser(String locale) {
            Faker faker = new Faker(new Locale(locale));
            UserInfo user = new UserInfo(generateCity(), generateFirstName(locale), generateLastName(locale), generatePhone(locale));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String firstName;
        String lastName;
        String phone;
    }
}
