package hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestWebDriver {
    WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
    }

    @Test
//public void testFindOtus() throws InterruptedException {
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("headless");
//
//    driver.get("https://duckduckgo.com/");
//    logger.info("Открыта страница duckduckgo.com");
//    driver.findElement(By.cssSelector("[class*='js-search-input search__input--adv']")).sendKeys("ОТУС");
//    driver.findElement(By.cssSelector("[class*='search__button  js-search-button']")).click();
//    Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение...", driver.findElement(By.xpath("//*[contains(text(), 'Онлайн‑курсы для профессионалов, дистанционное обучение...')]")).getText());
//}

    public void testOpenPicture() throws InterruptedException {


        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver.manage().window().fullscreen();
        driver.findElement(By.xpath("a//[@href='assets/images/p1.jpg']/parent::*[1]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[class*='pp_hoverContainer']")).isEnabled());
    }

    public void testGetCookie() throws InterruptedException {
        driver.manage().window().fullscreen();
        driver.get("https://otus.ru");
        String login = "tayrinn@mail.ru";
        String password = "Test12345";
        driver.findElement(By.cssSelector("[class*='header2__auth js-open-modal']")).click();
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).sendKeys(login);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
        logger.info("Авторизация прошла успешно");
        logger.info(driver.manage().getCookies());
    }
}
