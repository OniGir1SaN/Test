package com.demoqa.enums;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public enum TextElementsMain {
    MAIN_PAGE_TITLE1(By.xpath("//h1[@class='_info_mainTitle_1tksv_34']")),
    MAIN_PARAGRAPH1(By.xpath("//span[@class='_infor_description_1tksv_56']")),

    MAIN_PAGE_TITLE2(By.xpath("//h2[@class='_title_1r6r8_23']")),
    MAIN_PARAGRAPH2(By.xpath("//span[@class='_description_1r6r8_33']")),
    MAIN_PARAGRAPH3(By.xpath("//h2[@class='_text_title_w6vfm_68']")),
    MAIN_PARAGRAPH4(By.xpath("//p[@class='_text_description_w6vfm_83']")),
    MAIN_PARAGRAPH5(By.xpath("//h2[@class='_text_title_lrqgu_64']")),
    MAIN_PARAGRAPH6(By.xpath("//p[@class='_text_description_lrqgu_79']")),
    MAIN_PARAGRAPH7(By.xpath("//h2[@class='_text_title_jalcp_70']")),
    MAIN_PARAGRAPH8(By.xpath("//p[@class='_text_description_jalcp_85']")),
    MAIN_PARAGRAPH9(By.xpath("//h2[@class='_text_title_1p9h4_78']")),
    MAIN_PARAGRAPH10(By.xpath("//p[@class='_text_description_1p9h4_93']")),

    MAIN_PAGE_TITLE3(By.xpath("//h2[@class='_top_title_1jrkj_42']")),
    MAIN_PARAGRAPH11(By.xpath("//p[@class='_top_description_1jrkj_57']")),
    MAIN_PARAGRAPH12(By.xpath("//h3[@class='_bottomTitle_1jrkj_84']")),
    MAIN_PARAGRAPH13(By.xpath("(//h4[@class='_head_title_10d47_28'])[1]")),
    MAIN_PARAGRAPH14(By.xpath("(//p[@class='_infoText_10d47_38'])[1]")),
    MAIN_PARAGRAPH15(By.xpath("(//h4[@class='_head_title_10d47_28'])[2]")),
    MAIN_PARAGRAPH16(By.xpath("(//p[@class='_infoText_10d47_38'])[2]")),
    MAIN_PARAGRAPH17(By.xpath("(//h4[@class='_head_title_10d47_28'])[3]")),
    MAIN_PARAGRAPH18(By.xpath("(//p[@class='_infoText_10d47_38'])[3]")),;



    private final By locator;

    TextElementsMain(By locator) {
        this.locator = locator;
    }

    public WebElement getElement(WebDriver driver) {
        return driver.findElement(locator);
    }
}
