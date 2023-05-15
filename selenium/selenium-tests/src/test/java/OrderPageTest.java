import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderPageTest {

    private static String APP_URL = "http://127.0.0.1:3000/";
    private static String ORDER_URL_SUFFIX = "order";

    private WebDriver driver;

    @BeforeEach
    public void setDriver(){
        System.out.println("setting driver");
        driver = new FirefoxDriver();
    }

    @Test
    public void shouldBeEmptyAtNoBasketAddition() throws InterruptedException {
        driver.get(APP_URL+ORDER_URL_SUFFIX);
        List<WebElement> products = driver.findElements(By.id("basket-product"));
        String emptyMsg = driver.findElement(By.id("empty-basket-msg")).getText();

        assertEquals("nothing in basket",emptyMsg);

        driver.close();

    }

    @Test
    public void shouldSeeBasketFromProductsPage() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        String productName = driver.findElement(By.id("product-name")).getText();
        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);
        String afterPageChangeProductName = driver.findElement(By.id("basket-product")).getText();

        assertEquals(productName,afterPageChangeProductName);

        driver.close();
    }

    @Test
    public void shouldNotSeeProductRemovedInProducts() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("rmFromBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("nav-orders")).click();

        List<WebElement> products = driver.findElements(By.id("basket-product"));

        assertEquals(products.size(), 0);
        driver.close();
    }

    @Test
    public void shouldIncreaseAmountInBasket() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("incBasket")).click();
        String amount = driver.findElement(By.id("amount")).getText();

        assertTrue(amount.contains("2"));
        driver.close();

    }

    @Test
    public void shouldDecreaseAmountInBasket() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        driver.findElement(By.id("addToBasket")).click();

        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("decBasket")).click();
        String amount = driver.findElement(By.id("amount")).getText();

        assertTrue(amount.contains("1"));
        driver.close();
    }

    @Test
    public void shouldRemoveItemFromOrderAtZero() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();

        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("decBasket")).click();

        List<WebElement> products = driver.findElements(By.id("basket-product"));

        assertEquals(products.size(), 0);
        driver.close();
    }

    @Test
    public void shouldConfirmOrder() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();

        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("but-order")).click();
        TimeUnit.SECONDS.sleep(3);

        String status = driver.findElement(By.id("status-msg")).getText();

        assertTrue(status.contains("Order sucessful"));
        driver.close();

    }
//
    @Test
    public void shouldGiveLinkToComeBackAfterOrder() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();

        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("but-order")).click();
        TimeUnit.SECONDS.sleep(4);

        driver.findElement(By.id("comeback-link")).click();
        TimeUnit.SECONDS.sleep(1);

        String currentUrl = driver.getCurrentUrl();

        assertTrue(currentUrl.endsWith("/"));
        driver.close();


    }
    @Test
    public void shouldEmptyBasketAfterOrder() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();

        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("but-order")).click();
        TimeUnit.SECONDS.sleep(3);

        List<WebElement> products = driver.findElements(By.id("basket-product"));
        assertEquals(0,products.size());

        driver.close();
    }

    @Test
    public void shouldIncreaseAmountInBasketAndSeeIncreasedAmountAfterComebackToProducts() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();

        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("incBasket")).click();
        driver.findElement(By.id("nav-products")).click();

        String amount = driver.findElement(By.id("amount")).getText();

        assertTrue(amount.contains("2"));
        driver.close();
    }


}
