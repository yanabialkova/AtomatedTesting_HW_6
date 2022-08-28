package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RefillPage {
    SelenideElement amountField = $("[data-test-id=amount] input");
    SelenideElement fromField = $("[data-test-id=from] input");
    SelenideElement refillButton = $("[data-test-id=action-transfer]");
    SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public RefillPage() {}

    public void refill(String fromCard, int amount) {
        amountField.setValue(amount + "");
        fromField.setValue(fromCard);
        refillButton.click();
    }


}
