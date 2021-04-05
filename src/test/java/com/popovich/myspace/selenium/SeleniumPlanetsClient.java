package com.popovich.myspace.selenium;

import org.openqa.selenium.By;

public class SeleniumPlanetsClient {

    private SeleniumConfig config;
    private String url = "http://localhost:8081/gui/planets";

    public SeleniumPlanetsClient() {
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }

    public void closeWindow() {
        this.config.getDriver().close();
    }

    public String getTitle() {
        return this.config.getDriver().getTitle();
    }

    public String getPageNameText() {
        return config.getDriver().findElement(By.id("page-name")).getText();
    }
}