package guru.qa.tests.demoqa;

import com.codeborne.selenide.WebDriverRunner;
import guru.qa.pages.RegistrationPage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class WebSteps {

    RegistrationPage registrationPage = new RegistrationPage();

    @Step("Открываем основную страницу")
    public void openMainPage() {
        registrationPage.openPage();
    }

    @Step("Заполнение основных полей")
    public void fillingFields(String firstName, String lastName, String email, String gender, String mobile) {
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile);
    }

    @Step("Нажатие кнопки submit")
    public void buttonPress() {
        registrationPage.clickSubmit();
    }

    @Step("Проверка открытия окна")
    public void checkWindow() {
        registrationPage.checkModalForm();
    }

    @Step("Проверка заполнения полей")
    public void checkFormWindow(String firstName, String lastName, String email, String gender, String mobile) {
        registrationPage.checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", email)
                .checkForm("Gender", gender)
                .checkForm("Mobile", mobile);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
