package qa.guru;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class JopaPage {
    private final SelenideElement readingCheckbox = $("[for=hobbies-checkbox-2]");

    public void setName(String name) {
        $("[id=firstName]").setValue(name);
    }

    public void setLastName(String lastName) {
        $("#lastName").setValue(lastName);
    }

    public void clickOnReadingCheckbox() {
        readingCheckbox.click();
    }

    public void jopa() {

    }
}
