package org.example.steps

import org.example.pages.CheckOutPage
import org.example.pages.DressesPage
import org.example.pages.MyShopHomePage
import org.example.pages.TShirtsPage
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert


class MyStoreSteps(
    private val action: Actions,
    private val wait: WebDriverWait,
    private val driver: WebDriver,
    private val descList: MutableList<String>
) {
    fun openWomanGraph(myShopHomePage: MyShopHomePage) {
        val womanButton = myShopHomePage.womanPage()
        val tshirtButton = myShopHomePage.tshirtPage()

        action.moveToElement(womanButton).build().perform()
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a")))
        tshirtButton?.click()
    }

    fun openDisplayedTShirt(tShirtsPage: TShirtsPage) {
        val tshirtItem = tShirtsPage.tshirtItem()

        val quickViewButton = tShirtsPage.quickViewPage()
        action.moveToElement(tshirtItem).build().perform()
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[2]")))
        quickViewButton?.click()
    }

    fun addTShirtToCart(tShirtsPage: TShirtsPage) {
        // check out small pictures
        val tshirtPics = tShirtsPage.tshirtPics()
        for (pic in tshirtPics!!) {
            val picSelector = pic.getAttribute("id")
            val tshirtPic = driver.findElement(By.id(picSelector))
            tshirtPic?.click()
        }
        // select M size
        tShirtsPage.selectSize("M")

        // increase quantity by one
        val tshirtQuantityAdd = tShirtsPage.tshirtQuantity()
        tshirtQuantityAdd?.click()

        // add the item to the cart
        val addToCartButton = tShirtsPage.addToCartButton()
        val tshirtDesc = tShirtsPage.tshirtDesc()
        tshirtDesc?.let { descList.add(it.text) }
        addToCartButton?.click()
    }

    fun openDressesGraph(myShopHomePage: MyShopHomePage) {
        val dressesButton = myShopHomePage.dressesPage()
        val casualDressButton = myShopHomePage.casualDressPage()
        action.moveToElement(dressesButton).build().perform()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#block_top_menu > ul > li:nth-child(2) > ul > li:nth-child(1) > a")))
        casualDressButton?.click()
    }

    fun addDressToCart(dressesPage: DressesPage) {
        val dressItem = dressesPage.dressItem()
        val addToCartButton = dressesPage.addToCartButton()
        action.moveToElement(dressItem).build().perform()
        val dressDesc = dressesPage.dressDesc()
        dressDesc?.let { descList.add(it.text) }
        addToCartButton?.click()
    }

    fun checkDescriptionsAndProceed(checkOutPage: CheckOutPage) {
        val cartItems = checkOutPage.cartItems()
        for (item in cartItems!!) {
            Assert.assertTrue(descList.contains(item.text))
        }
        val proceedToCheckoutButton = checkOutPage.proceedToCheckout()
        proceedToCheckoutButton?.click()
    }
}
