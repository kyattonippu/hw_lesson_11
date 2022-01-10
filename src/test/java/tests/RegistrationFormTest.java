package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class RegistrationFormTest extends TestBase {

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = "test@test.com",
            gender = "Female",
            number = "0987654321",
            subject = "Biology",
            hobby = "Reading",
            path = "src/test/resources/", fileName = "testimage.jpg",
            streetAddress = faker.address().streetAddress(),
            state = "Rajasthan", city = "Jaipur";

    @Test
    void fullFormTest() {
        step("Open students registration form", () -> {
            registrationPage.openPage();
        });

        step("Fill students registration form", () -> {
            step("Fill common data", () -> {
                registrationPage.typeFirstName(firstName)
                        .typeLastName(lastName)
                        .typeUserEmail(email)
                        .chooseGender(gender)
                        .typeUserNumber(number);
            });
            step("Set date", () -> registrationPage.calendar.setDate("10", "January", "1998"));
            step("Set subjects", () -> registrationPage.typeSubjects(subject));
            step("Set Hobbies", () -> registrationPage.setHobbies(hobby));
            step("Upload image", () -> registrationPage.uploadFile(path + fileName));
            step("Set address", () -> {
                registrationPage.typeAddress(streetAddress)
                        .selectState(state)
                        .selectCity(city);
            });
            step("Submit form", () -> registrationPage.submit());
            step("Check modal form", () -> registrationPage.checkModalForm());
        });

        step("Verify successful form submit",() -> {
            registrationPage
                    .checkResultsValue("Student Name", firstName + " " + lastName)
                    .checkResultsValue("Student Email", email)
                    .checkResultsValue("Gender", gender)
                    .checkResultsValue("Mobile", number)
                    .checkResultsValue("Date of Birth", "10 January,1998")
                    .checkResultsValue("Subjects", subject)
                    .checkResultsValue("Hobbies", hobby)
                    .checkResultsValue("Picture", fileName)
                    .checkResultsValue("Address", streetAddress)
                    .checkResultsValue("State and City", state + " " + city);
        });
    }
}

