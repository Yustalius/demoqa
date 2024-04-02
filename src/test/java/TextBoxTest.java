import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.RegistrationPage;

public class TextBoxTest {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadTimeout = 100000;
    }

/*    @CsvSource({
            "Vladislav, Yustus, deles2013@yandex.ru, Male, 7920631395, 02, June, 2003, Math, Hindi, Sports, Music, ul Keramzavoda d 6 kv 2, NCR, Delhi",
            "Anton, GonDone, sharik228@yandex.ru, Female, 7000631395, 01, December, 2004, Math, Hindi, Sports, Music, ul Keramzavoda d 6 kv 2, NCR, Delhi"
    })*/
    @CsvFileSource(resources = "/parametrs.csv")
    @ParameterizedTest(name = "{0} {1}")
    void demoQAAuthFormTest(
            String userName,
            String userLastName,
            String userEmail,
            String gender,
            String mobileNumber,
            String day,
            String month,
            String year,
            String firstSubject,
            String secondSubject,
            String firstHobby,
            String secondHobby,
            String address,
            String state,
            String city
    ) {
/*        String userName = "Vladislav",
                userLastName = "Yustus",
                userEmail = "deles2013@yandex.ru",
                gender = "Male",
                mobileNumber = "7920631395",
                day = "02",
                month = "June",
                year = "2003",
                firstSubject = "Math",
                secondSubject = "Hindi",
                firstHobby = "Sports",
                secondHobby = "Music",
                address = "ul.Keramzavoda d.6. kv.2",
                state = "NCR",
                city = "Delhi";*/


        registrationPage.openPage()
                .setFirstName(userName)
                .setLastName(userLastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setMobileNumber(mobileNumber)
                .setBirthDate(day, month, year)
                .setSubject(firstSubject, secondSubject)
                .setHobbies(firstHobby, secondHobby)
                .uploadFile()
                .setAddress(address)
                .setStateAndCity(state, city)
                .submitResults();

        registrationPage.verifyResultsModalAppears().
                verifyResult("Student Name", userName + " " + userLastName).
                verifyResult("Student Email", userEmail).
                verifyResult("Gender", gender).
                verifyResult("Mobile", mobileNumber);

    }
}
