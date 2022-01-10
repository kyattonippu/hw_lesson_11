package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    // locators & elements
    private final String FORM_TITLE = "Student Registration Form";
    private final String MODAL_FORM_TITLE_AFTER_SUBMIT = "Thanks for submitting the form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userGenderRadio = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            checkboxHobbies = $("#hobbiesWrapper"),
            uploadFileButton = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            selectState = $("#state"),
            selectCity = $("#city"),
            submitButton = $("#submit"),
            modalForm = $("#example-modal-sizes-title-lg"),
            resultsTable = $(".table-responsive");

    public CalendarComponent calendar = new CalendarComponent();

    // actions
    public RegistrationPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));

        return this;
    }

    public RegistrationPage typeFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationPage typeLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationPage typeUserEmail(String email) {
        userEmailInput.setValue(email);

        return this;
    }

    public RegistrationPage chooseGender(String gender) {
        userGenderRadio.$(byText(gender)).click();

        return this;
    }

    public RegistrationPage typeUserNumber(String number) {
        userNumber.setValue(number);

        return this;
    }

    public RegistrationPage typeSubjects(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String hobbies) {
        checkboxHobbies.$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage uploadFile(String path) {
        uploadFileButton.uploadFile(new File(path));
        return this;
    }

    public RegistrationPage typeAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public RegistrationPage selectState(String state) {
        selectState.scrollIntoView(true);
        selectState.click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        selectCity.click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationPage submit() {
        submitButton.scrollIntoView(true);
        submitButton.click();
        return this;
    }

    public RegistrationPage checkModalForm() {
        modalForm.shouldHave(text(MODAL_FORM_TITLE_AFTER_SUBMIT));

        return this;
    }

    public RegistrationPage checkResultsValue(String key, String value) {
        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));

        return this;
    }
}
