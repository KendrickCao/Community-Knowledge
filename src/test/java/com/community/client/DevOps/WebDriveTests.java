//package com.community.client;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class WebDriveTests {
//    private WebDriver webDriver;
//
//    @Value("${local.server.port}")
//    private int port;
//
//    @Test
//    public void testingPageContents() {
////      an alternative really good resource:   https://github.com/bonigarcia/webdrivermanager
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\c21106784\\OneDrive - Cardiff University\\Study\\CMT654-DevOps\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-debugging-port=42227");
//        options.addArguments("--headless");
//        this.webDriver = new ChromeDriver(options);
//        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Login");
//        assertTrue(webDriver.findElement(By.id("loginUser-button")).getText().contains("LOGIN"));
//
////        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Admin/AddItem");
////        this.webDriver.findElement(By.name("username")).sendKeys("user");
////        this.webDriver.findElement(By.name("password")).sendKeys("password");
////        this.webDriver.findElement(By.tagName("button")).click();
////        assertTrue(webDriver.findElement(By.cssSelector("main h1")).getText().contains("ADMIN Pages"));
////
////        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Menu");
////        assertTrue(webDriver.findElement(By.cssSelector("table")).getText().contains("mockDBChips"));
//
//        webDriver.quit();
//    }
//}
