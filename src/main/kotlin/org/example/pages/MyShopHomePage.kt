package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class MyShopHomePage(private val driver: WebDriver) {

    private var element: WebElement? = null

    fun womanPage(): WebElement? {
        val womanPageSelector = "#block_top_menu > ul > li:nth-child(1)"
        element = driver.findElement(By.cssSelector(womanPageSelector))
        return element
    }

    fun tshirtPage(): WebElement? {
        val tshirtPageSelector = "//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a"
        element = driver.findElement(By.xpath(tshirtPageSelector))
        return element
    }

    fun dressesPage(): WebElement? {
        val dressesPageSelector = "#block_top_menu > ul > li:nth-child(2) > a"
        element = driver.findElement(By.cssSelector(dressesPageSelector))
        return element
    }

    fun casualDressPage(): WebElement?{
        val casualDressPageSelector = "#block_top_menu > ul > li:nth-child(2) > ul > li:nth-child(1) > a"
        element = driver.findElement(By.cssSelector(casualDressPageSelector))
        return element
    }
}