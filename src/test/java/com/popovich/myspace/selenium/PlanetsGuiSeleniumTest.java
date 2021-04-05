package com.popovich.myspace.selenium;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,properties = { "server.port=8081" })
public class PlanetsGuiSeleniumTest {

    private static SeleniumPlanetsClient seleniumPlanetsClient;

    @BeforeEach
    public  void setUp() {
        seleniumPlanetsClient = new SeleniumPlanetsClient();
    }

    @AfterEach
    public  void tearDown() {
        seleniumPlanetsClient.closeWindow();
    }

    @Test
    public void testCanReadPlanetPageTitle() {
        String actualTitle = seleniumPlanetsClient.getTitle();

        assertNotNull(actualTitle);
        assertEquals("All planets", actualTitle);
    }

    @Test
    public void testCanReadPageNameFromDOM() {
        String pageName = seleniumPlanetsClient.getPageNameText();
        assertEquals("Planets",pageName);
    }
}