import enums.UsersData;
import helpers.UserGenerator;
import hooks.BaseHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.otus.LoginPage;
import pages.otus.MainPage;
import pages.otus.PersonalPage;

import java.util.HashMap;
import java.util.Map;

class FourthHomeworkTest extends BaseHooks {
    private static final Logger LOGGER = LogManager.getLogger(FourthHomeworkTest.class);

    @Test
    void checkOtusProfileInfo() {
        // Генерируем и запоминаем данные пользователя OTUS
        UserGenerator userData = new UserGenerator();
        userData.setUser();
        Map<UsersData, String> user = userData.getUser();
        LOGGER.info("Получены данные пользователя");
        // Step 1 - Открыть https://otus.ru, авторизоваться на сайте
        authToOtus(userData.getLogin(),userData.getPassword());
        // Step 2 - Войти в личный кабинет
        PersonalPage personalPage = openUserPersonalPage();
        // Step 3 - В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        // Добавляем 3 контакта разного типа пользователю
        addNewContact(0, "Тelegram", user.get(UsersData.FIRST_PHONE), personalPage);
        personalPage.addContactField();
        addNewContact(1, "Viber", user.get(UsersData.SECOND_PHONE), personalPage);
        personalPage.addContactField();
        addNewContact(2, "WhatsApp", user.get(UsersData.THIRD_PHONE), personalPage);
        // Step 4 - Нажать сохранить
        personalPage.saveAndFillLater();
        LOGGER.info("Личный данные пользователя сохранены");
        // Step 5 - Открыть https://otus.ru в "чистом браузере"
        openNewBrowserSession();
        // Step 6 - Открыть https://otus.ru, авторизоваться на сайте
        authToOtus(userData.getLogin(),userData.getPassword());
        // Step 7 - Войти в личный кабинет
        PersonalPage newPersonalPage = openUserPersonalPage();
        Assertions.assertAll(
                () -> checkContactInfo(0, "Тelegram", user.get(UsersData.FIRST_PHONE), newPersonalPage),
                () -> checkContactInfo(1, "Viber", user.get(UsersData.SECOND_PHONE), newPersonalPage),
                () -> checkContactInfo(2, "WhatsApp", user.get(UsersData.THIRD_PHONE), newPersonalPage)
        );
    }

    private void openNewBrowserSession() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        LOGGER.info("Открыта новая сессия браузера");
    }

    private PersonalPage openUserPersonalPage() {
        MainPage mainPage = new MainPage(driver);
        PersonalPage page = mainPage.openHeaderMenu().openPersonalPage();
        LOGGER.info("Открыта страница: \"{}\"", page.getPageTitle());
        return page;
    }

    private void authToOtus(String login, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open().loginToOtus(login, password);
        LOGGER.info("Выполнен вход пользователем \"{}\"", login);
    }

    private void addNewContact(int contactOrder, String contactType, String contact, PersonalPage page) {
        page.selectDropdownValue(By.xpath(String.format(PersonalPage.CONTACT_TYPE_PATTERN, contactOrder)), contactType);
        page.fillField(By.xpath(String.format(PersonalPage.CONTACT_FIELD_PATTERN, contactOrder)), contact);
        LOGGER.info("Добавлен новый контакт: \"{}\" = {}", contactType, contact);
    }

    private void checkContactInfo(int contactOrder, String expectedContactType, String expectedVal, PersonalPage page) {
        String currentContactType = page.getElementText(
                By.xpath(String.format(PersonalPage.CONTACT_TYPE_PATTERN, contactOrder)));
        String currentValue = page.getWebElementAttribute(
                By.xpath(String.format(PersonalPage.CONTACT_FIELD_PATTERN, contactOrder)), "value");
        Map<String, String> currentContactData = new HashMap<>();
        currentContactData.put(currentContactType, currentValue);
        Map<String, String> expectedContactData = new HashMap<>();
        expectedContactData.put(expectedContactType, expectedVal);
        Assertions.assertEquals(expectedContactData, currentContactData, "Значения не совпали!");
        LOGGER.info("Контакт \"{}\" равен {}", currentContactType, currentValue);
    }
}
