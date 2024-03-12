import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTest {

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void Test() {
        open("/automation-practice-form");
        $(".text-center").shouldHave(text("Practice Form"));

        $("#firstName").setValue("Vladislav");
        $("#lastName").setValue("Yustus");
        $("#userEmail").setValue("deles2013@yandex.ru");
        $("#userNumber").setValue("79206313951");
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("Hindi").pressEnter();

        $(byText("Male")).click();
        $("#dateOfBirthInput").click();
        $(byText("June")).click();
        $(byText("2003")).click();
        $("[aria-label='Choose Monday, June 2nd, 2003']").click();

        $$(".custom-control.custom-checkbox.custom-control-inline").first().click();
        $(byText("Music")).click();

        File file = new File("src/test/resources/star.png");
        $("input#uploadPicture").uploadFile(file);

        $("#currentAddress").setValue("ul.Keramzavoda, d.6. kv.2");
        $(byText("Select State")).click();
        $(byText("NCR")).click();

        $(byText("Select City")).click();
        $(byText("Noida")).click();

        $("#submit").click();

        sleep(5000);




    }
}
