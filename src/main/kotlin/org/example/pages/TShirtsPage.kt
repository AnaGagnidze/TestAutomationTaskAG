package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

class TShirtsPage(private val driver: WebDriver) {

    private var element: WebElement? = null
    private var elements: List<WebElement>? = null

    fun tshirtItem(): WebElement? {
        val tshirtItemSelector = "//*[@id=\"center_column\"]/ul/li/div/div[1]"
        element = driver.findElement(By.xpath(tshirtItemSelector))
        return element
    }

    fun quickViewPage(): WebElement? {
        val quickViewPageSelector = "//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[2]"
        element = driver.findElement(By.xpath(quickViewPageSelector))
        return element
    }

    fun tshirtPics(): List<WebElement>? {
        val tshirtPicsSelector = "//*[@id=\"thumbs_list_frame\"]"
        elements = driver.findElements(By.xpath(tshirtPicsSelector))
        return elements
    }

    fun selectSize(value: String) {
        val allSizesSelector = "group_1"
        element = driver.findElement(By.id(allSizesSelector))
        val dropdown = Select(element)
        dropdown.selectByVisibleText(value)
    }

    fun tshirtQuantity(): WebElement? {
        val tshirtQuantitySelector = "//*[@id=\"quantity_wanted_p\"]/a[2]/span/i"
        element = driver.findElement(By.xpath(tshirtQuantitySelector))
        return element
    }

    fun addToCartButton(): WebElement? {
        val addToCartButtonSelector = "#add_to_cart > button"
        element = driver.findElement(By.cssSelector(addToCartButtonSelector))
        return element

    }

    fun continueShoppingButton(): WebElement? {
        val continueShoppingButtonSelector =
            "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span"
        element = driver.findElement(By.cssSelector(continueShoppingButtonSelector))
        return element
    }

    fun homePageIcon(): WebElement? {
        val homePageIconSelector = "#header_logo > a > img"
        element = driver.findElement(By.cssSelector(homePageIconSelector))
        return element
    }

    fun tshirtDesc(): WebElement? {
        val tshirtDescSelector = "#product > div > div > div.pb-center-column.col-xs-12.col-sm-4 > h1"
        element = driver.findElement(By.cssSelector(tshirtDescSelector))
        return element
    }
}