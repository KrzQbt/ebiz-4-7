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

public class ProductPageTest {

    private static String APP_URL = "http://127.0.0.1:3000/";
    private static WebDriver driver;

    @BeforeEach
    public void setDriver(){
        System.out.println("setting driver");
        driver = new FirefoxDriver();
    }


    @Test
    public void shouldLoadProducts() throws InterruptedException {
        driver.get(APP_URL);

        TimeUnit.SECONDS.sleep(3);

        List<WebElement> productNames = driver.findElements(By.id("product-name"));

        String name = productNames.get(0).getText();

        assertTrue( productNames.size() > 0);
        assertTrue(name.length() > 0);
        driver.close();

    }

    @Test
    public void shouldDisplayBasketOnSide() throws InterruptedException {

        driver.get(APP_URL);
        TimeUnit.SECONDS.sleep(3);
        String basket = driver.findElement(By.id("basket-header")).getText();

        assertTrue(basket.contains("Basket"));

        driver.close();
    }

    @Test
    public void shouldDisplayNav() {
        driver.get(APP_URL);
        String products = driver.findElement(By.id("nav-products")).getText();

        String order = driver.findElement(By.id("nav-orders")).getText();

        assertTrue(products.contains("Products"));
        assertTrue(order.contains("Order"));

        driver.close();
    }

    @Test
    public void shouldAddProductsToBasket() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        String product = driver.findElement(By.id("basket-product")).getText();
        assertTrue(product.length() > 0);

        driver.close();

    }
    @Test
    public void shouldSubtract1PieceProductFromBasket() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("rmFromBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        List<WebElement> products = driver.findElements(By.id("basket-product"));

        assertEquals(products.size(), 0);
        driver.close();
    }


    @Test
    public void shouldNotPlaceNegativeAmountsInBasket() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("rmFromBasket")).click();
        driver.findElement(By.id("rmFromBasket")).click();
        List<WebElement> products = driver.findElements(By.id("basket-product"));


        assertEquals(products.size(), 0);
        driver.close();
    }

    @Test
    public void shouldRemoveProductFromBasketOnChangeToZero() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("rmFromBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        List<WebElement> products = driver.findElements(By.id("basket-product"));

        assertEquals(products.size(), 0);
        driver.close();
    }

    @Test
    public void shouldRouteToOrderViaNav() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("nav-orders")).click();
        TimeUnit.SECONDS.sleep(1);

        String currentUrl = driver.getCurrentUrl();

        assertTrue(currentUrl.contains("order"));
        driver.close();

    }

    @Test
    public void shouldRouteToOrderViaBasket() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("but-orders")).click();
        TimeUnit.SECONDS.sleep(1);

        String currentUrl = driver.getCurrentUrl();

        assertTrue(currentUrl.contains("order"));

        driver.close();

    }

    @Test
    public void shouldKeepBasketOnSiteChangeViaNav() throws InterruptedException {
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
    public void shouldKeepBasketOnSiteChangeViaBasket() throws InterruptedException {
        driver.get(APP_URL);
        driver.findElement(By.id("addToBasket")).click();
        TimeUnit.SECONDS.sleep(1);
        String productName = driver.findElement(By.id("product-name")).getText();
        driver.findElement(By.id("but-orders")).click();
        TimeUnit.SECONDS.sleep(1);
        String afterPageChangeProductName = driver.findElement(By.id("basket-product")).getText();

        assertEquals(productName,afterPageChangeProductName);

        driver.close();
    }



}
