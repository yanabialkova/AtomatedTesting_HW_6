package test;

import data.DataHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyFromFirstToSecond() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var startingBalance1 = dashboardPage.getCardBalance(1);
        var startingBalance2 = dashboardPage.getCardBalance(0);

        var fromCard = DataHelper.getCard1();
        var amount = 5000;

        var refillPage = dashboardPage.refillCard(1);
        refillPage.refill(fromCard.getNumber(), amount);

        var finalBalance1 = dashboardPage.getCardBalance(1);
        var finalBalance2 = dashboardPage.getCardBalance(0);

        Assertions.assertEquals(startingBalance1 + amount, finalBalance1);
        Assertions.assertEquals(startingBalance2 - amount, finalBalance2);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var startingBalance1 = dashboardPage.getCardBalance(0);
        var startingBalance2 = dashboardPage.getCardBalance(1);

        var fromCard = DataHelper.getCard2();
        var amount = 5000;

        var refillPage = dashboardPage.refillCard(0);
        refillPage.refill(fromCard.getNumber(), amount);

        var finalBalance1 = dashboardPage.getCardBalance(0);
        var finalBalance2 = dashboardPage.getCardBalance(1);

        Assertions.assertEquals(startingBalance1 + amount, finalBalance1);
        Assertions.assertEquals(startingBalance2 - amount, finalBalance2);
    }

}
