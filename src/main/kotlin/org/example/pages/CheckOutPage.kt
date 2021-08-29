package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class CheckOutPage(private val driver: WebDriver) {
    private var elements: List<WebElement>? = null

    fun cartItems(): List<WebElement>? {
        val cartItemsSelector = "#cart_summary > tbody"
        elements = driver.findElements(By.cssSelector(cartItemsSelector))
        return elements
    }
}