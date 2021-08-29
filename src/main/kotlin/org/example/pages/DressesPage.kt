package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class DressesPage(private val driver: WebDriver) {
    private var element: WebElement? = null

    fun dressItem(): WebElement? {
        val dressItemSelector = "#center_column > ul > li > div > div.left-block"
        element = driver.findElement(By.cssSelector(dressItemSelector))
        return element
    }

    fun addToCartButton(): WebElement? {
        val addToCartButtonSelector =
            "#center_column > ul > li > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default > span"
        element = driver.findElement(By.cssSelector(addToCartButtonSelector))
        return element
    }
    fun continueShoppingButton(): WebElement? {
        val continueShoppingButtonSelector =
            "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span > span"
        element = driver.findElement(By.cssSelector(continueShoppingButtonSelector))
        return element
    }

    fun shoppingCartGraph(): WebElement?{
        val shoppingCartGraphSelector = "#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a"
        element = driver.findElement(By.cssSelector(shoppingCartGraphSelector))
        return element
    }

    fun checkOutButton(): WebElement? {
        val checkOutButtonSelector = "#button_order_cart > span"
        element = driver.findElement(By.cssSelector(checkOutButtonSelector))
        return element
    }

    fun dressDesc(): WebElement? {
        val dressDescSelector = "#center_column > ul > li > div > div.right-block > h5 > a"
        element = driver.findElement(By.cssSelector(dressDescSelector))
        return element
    }
}