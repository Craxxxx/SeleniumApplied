package ui_qa.helper;

import org.openqa.selenium.WebElement;

public class ProductSummary {
    private final String name;
    private final String price;
    private final WebElement img;

    //make a constructor
    public ProductSummary(String name, String price, WebElement img)
    {
        this.name = name;
        this.price = price;
        this.img = img;
    }

    //getter
    public String getName(){return this.name;}
    public String getPrice(){return this.price;}
    public WebElement getImg(){return this.img;}
}
