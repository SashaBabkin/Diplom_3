import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.pageobjects.ConstructorPage;

public class SectionChangeTest extends BaseTest {

    @Test
    @DisplayName("Switch to Buns section")
    @Description("Positive test of switching to Buns section")
    public void checkChangeToBunsSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.saucesLinkClick();
        constructorPage.checkBunsActive();
    }

    @Test
    @DisplayName("Switch to Sauces section")
    @Description("Positive test of switching to Sauces section")
    public void checkChangeToSaucesSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.checkCaucesActive();
    }

    @Test
    @DisplayName("Switch to Fillings section")
    @Description("Positive test of switching to Fillings section")
    public void checkChangeToFillingsSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.checkFillingsActive();
    }

}
