package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class CheckOutPage(private val driver: WebDriver) {
    private var elements: List<WebElement>? = null
    private var element: WebElement? = null


    fun cartItems(): List<WebElement>? {
        val cartItemsSelector = "#cart_summary .product-name a"
        elements = driver.findElements(By.cssSelector(cartItemsSelector))
        return elements
    }

    fun proceedToCheckout(): WebElement? {
        val proceedToCheckoutSelector =
            "#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium > span"
        element = driver.findElement(By.cssSelector(proceedToCheckoutSelector))
        return element
    }
}