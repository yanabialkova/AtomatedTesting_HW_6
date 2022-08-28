package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement header = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");

    public DashboardPage() {
        header.shouldBe(visible);
    }

    public RefillPage refillCard(int cardIndex) {
        SelenideElement card = cards.get(cardIndex);
        $$("[data-test-id=action-deposit]").get(cardIndex).click();

        return new RefillPage();
    }

    public int getCardBalance(int cardIndex) {
        SelenideElement card = cards.get(cardIndex);
        String text = card.getText();
        return extractBalance(text);
    }

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}