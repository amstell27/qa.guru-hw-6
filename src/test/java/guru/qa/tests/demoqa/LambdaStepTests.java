package guru.qa.tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.pages.RegistrationPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;


public class LambdaStepTests {

    RegistrationPage registrationPage = new RegistrationPage();
    String firstName = "Alex";
    String lastName = " Alexeev";
    String email = "emain@email.com";
    String gender = "Male";
    String mobile = "8002000500";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @DisplayName("Selenide (с Listener)")
    @Test
    void listenerSelenideTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile)
                .clickSubmit()
                .checkModalForm()
                .checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", email)
                .checkForm("Gender", gender)
                .checkForm("Mobile", mobile);
    }

    @DisplayName("Лямбда шаги через step")
    @Test
    void lambdaStepTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем основную страницу", () -> {
            registrationPage.openPage();
        });
        step("Заполнение основных полей", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setGender(gender)
                    .setMobile(mobile);
        });
        step("Нажатие кнопки submit", () -> {
            registrationPage.clickSubmit();
        });
        step("Проверка открытия окна", () -> {
            registrationPage.checkModalForm();
        });
        step("Проверка заполнения полей", () -> {
            registrationPage.checkForm("Student Name", firstName + " " + lastName)
                    .checkForm("Student Email", email)
                    .checkForm("Gender", gender)
                    .checkForm("Mobile", mobile);
        });

    }

    @DisplayName("Тест с помощью аннотаций @Step")
    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.fillingFields(firstName, lastName, email, gender, mobile);
        steps.buttonPress();
        steps.checkWindow();
        steps.checkFormWindow(firstName, lastName, email, gender, mobile);
        steps.takeScreenshot();
    }
}

