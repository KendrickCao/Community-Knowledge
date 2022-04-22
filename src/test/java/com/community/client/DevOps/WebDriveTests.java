//package com.community.client.DevOps;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class WebDriveTests {
//    private WebDriver webDriver;
//
//    @Value("${local.server.port}")
//    private int port;
//
//    @Test
//    public void testingPageContents() throws InterruptedException {
////      an alternative really good resource:   https://github.com/bonigarcia/webdrivermanager
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\c21106784\\OneDrive - Cardiff University\\Study\\CMT654-DevOps\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-debugging-port=42227");
//        options.addArguments("--headless");
//        this.webDriver = new ChromeDriver(options);
//        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Login");
//        assertTrue(webDriver.findElement(By.id("loginUser-button")).getText().contains("LOGIN"));
//
//        this.webDriver.findElement(By.name("email")).sendKeys("test123@gmail.com");
//        this.webDriver.findElement(By.name("password")).sendKeys("12345678");
//        this.webDriver.findElement(By.id("loginUser-button")).click();
//        this.webDriver.findElement(By.id("consent")).click();
//        Thread.sleep(2000);
//        this.webDriver.switchTo().alert().accept();
//        assertTrue(webDriver.findElement(By.className("company-name")).getText().contains("MDYEC"));
//
//        webDriver.quit();
//    }
//}
