package tests;

import helpers.DriverUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class AlfabankTests extends TestBase {
    @Test
    @DisplayName("Проверка свойства title главной страницы")
    void titleTest() {
        step("Открытие страницы 'https://alfabank.ru/'", () ->
                open("https://alfabank.ru/"));

        step("Свойство title главной страницы сайта должно быть равно 'Альфа-Банк - кредитные и дебетовые карты, кредиты наличными, автокредитование, ипотека и другие банковские услуги физическим и юридическим лицам – Альфа-Банк'", () -> {
            String expectedTitle = "Альфа-Банк - кредитные и дебетовые карты, кредиты наличными, автокредитование, ипотека и другие банковские услуги физическим и юридическим лицам – Альфа-Банк";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Console log на главной странице не должна содержать ошибок")
    void consoleShouldNotHaveErrorsTest() {
        step("Открытие страницы 'https://alfabank.ru/'", () ->
                open("https://alfabank.ru/"));

        step("Console logs не должена содержать 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

    @Test
    @Description("Поиск заголовка 'Популярные продукты'")
    void checkHeading() {
        step("Открытие страницы 'https://alfabank.ru/'", () ->
                open("https://alfabank.ru/"));

        step("Проверка заголовка 'Популярные продукты'", () -> {
            $(".hfPeB").shouldHave(text("Популярные продукты"));
        });
    }

    @Test
    @Description("Переход на страницу 'О банке'")
    void checkOpenPage() {
        step("Открытие страницы 'https://alfabank.ru/'", () ->
                open("https://alfabank.ru/"));

        step("Переход на страницу 'О банке'", () -> {
            $$("._3ghIc li").findBy(text("О банке")).click();
        });
        step("Свойство title открытой страницы сайта должно быть равно 'Информация о банке «Альфа-Банк»'", () -> {
            String expectedTitle = "Информация о банке «Альфа-Банк»";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Description("Переход на страницу 'Кредит на ремонт квартиры'")
    void checkOpenPageCredit() {
        step("Открытие страницы 'https://alfabank.ru/'", () ->
                open("https://alfabank.ru/"));

        step("Открытие выпадающего списка", () -> {
            $("._2hqQz").click();
        });
        step("Смена значения на 'Ремонт квартиры'", () -> {
            $$("._9hqbv li").findBy(text("Ремонт квартиры")).click();
        });
        step("Клик по кнопке 'Начать ремонт'", () -> {
            $("._3_FDm").click();
        });
        step("Свойство title открытой страницы сайта должно быть равно 'Кредит на ремонт квартиры от 5,5% - взять онлайн потребительский кредит на ремонт жилья, оформить онлайн заявку в «Альфа-Банк»'", () -> {
            String expectedTitle = "Кредит на ремонт квартиры от 5,5% - взять онлайн потребительский кредит на ремонт жилья, оформить онлайн заявку в «Альфа-Банк»";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }
}
