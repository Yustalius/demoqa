package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultsModal;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();

    private final String TITLE_TEXT = "Practice Form";
    private final String FILE_ADDRESS = "src/test/resources/star.png";
    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadio = $("#genterWrapper"),
            mobileNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            fileUpload = $("input#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateDropdown = $("#stateCity-wrapper").$("#state"),
            submitButton = $("#submit");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".text-center").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderRadio.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setMobileNumber(String value) {
        mobileNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubject(String firstSubject, String secondSubject) {
        subjectsInput.setValue(firstSubject).pressEnter();
        subjectsInput.setValue(secondSubject).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies(String firstHobby, String secondHobby) {
        hobbiesInput.$(byText(firstHobby)).click();
        hobbiesInput.$(byText(secondHobby)).click();

        return this;
    }

    public RegistrationPage uploadFile() {
        File file = new File(FILE_ADDRESS);
        fileUpload.uploadFile(file);

        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        $("#state").click();
        stateDropdown.$(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        return this;
    }

    public RegistrationPage submitResults() {
        submitButton.click();

        return this;
    }


    public RegistrationPage verifyResultsModalAppears() {
        registrationResultsModal.verifyModalAppears();

        return this;
    }

    public RegistrationPage verifyResult(String key, String value) {
        registrationResultsModal.verifyResult(key, value);

        return this;
    }



}
