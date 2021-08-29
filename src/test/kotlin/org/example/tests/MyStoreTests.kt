package org.example.tests

import org.example.pages.CheckOutPage
import org.example.pages.DressesPage
import org.example.pages.MyShopHomePage
import org.example.pages.TShirtsPage
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import java.time.Duration

class MyStoreTests {

    private var driver: WebDriver? = null
    private lateinit var myShopHomePage: MyShopHomePage
    private lateinit var tShirtsPage: TShirtsPage
    private lateinit var dressesPage: DressesPage
    private lateinit var checkOutPage: CheckOutPage
    private val url = "http://automationpractice.com/index.php"
    private val descList: MutableList<String> = mutableListOf()


    @Test
    fun mainTest() {
        // open home page
        driver?.get(url)
        driver?.manage()?.window()?.maximize()
        Assert.assertEquals(driver?.currentUrl, url, "Urls did not match.")


        val action = Actions(driver)
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        // open woman graph and navigate to T-shirts section
        myShopHomePage = MyShopHomePage(driver!!)
        openWomanGraph(action, myShopHomePage)

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]")))

        // open displayed tshirt on a tshirt page
        tShirtsPage = TShirtsPage(driver!!)
        openDisplayedTShirt(action, tShirtsPage)

        val iframe = driver?.findElement(By.cssSelector(".fancybox-inner iframe"))
        driver?.switchTo()?.frame(iframe)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"thumbs_list_frame\"]")))

        // Add selected tshirt to cart
        addTShirtToCart(driver!!, tShirtsPage)
        driver?.switchTo()?.defaultContent()

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span")))

        // click continue shopping button
        val continueShoppingButton = tShirtsPage.continueShoppingButton()
        continueShoppingButton?.click()

        // Go back to home page
        val homePageIcon = tShirtsPage.homePageIcon()
        homePageIcon?.click()

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#block_top_menu > ul > li:nth-child(1)")))
        // open dresses graph from home page and choose casual dresses option
        openDressesGraph(action, myShopHomePage)

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#center_column > ul > li > div > div.left-block")))
        // add the dress to the cart
        dressesPage = DressesPage(driver!!)
        addDressToCart(action, dressesPage)

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span > span")))

        // click continue shopping button
        val continueShoppingButtonDress = dressesPage.continueShoppingButton()
        continueShoppingButtonDress?.click()

        // check out
        val shoppingCartGraph = dressesPage.shoppingCartGraph()
        val checkOutButton = dressesPage.checkOutButton()
        action.moveToElement(shoppingCartGraph).build().perform()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#button_order_cart > span")))
        checkOutButton?.click()

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#cart_summary > tbody")))
//
//        checkOutPage = CheckOutPage(driver!!)
//        val cartItems = checkOutPage.cartItems()
//        for (item in cartItems!!){
//            val trTag = driver?.findElement(By.id(item.getAttribute("id")))
//            val cartItemDesc = trTag?.findElement(By.tagName("a"))
//            descList.contains(cartItemDesc?.text)
//        }
    }

    private fun openWomanGraph(action: Actions, myShopHomePage: MyShopHomePage) {
        val womanButton = myShopHomePage.womanPage()
        val tshirtButton = myShopHomePage.tshirtPage()

        action.moveToElement(womanButton).build().perform()
        tshirtButton?.click()
    }

    private fun openDisplayedTShirt(action: Actions, tShirtsPage: TShirtsPage){
        val tshirtItem = tShirtsPage.tshirtItem()

        val quickViewButton = tShirtsPage.quickViewPage()
        action.moveToElement(tshirtItem).build().perform()
        quickViewButton?.click()
    }

    private fun addTShirtToCart(driver: WebDriver, tShirtsPage: TShirtsPage){
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

    private fun openDressesGraph(action: Actions, myShopHomePage: MyShopHomePage){
        val dressesButton = myShopHomePage.dressesPage()
        val casualDressButton = myShopHomePage.casualDressPage()
        action.moveToElement(dressesButton).build().perform()
        casualDressButton?.click()
    }

    private fun addDressToCart(action: Actions, dressesPage: DressesPage){
        val dressItem = dressesPage.dressItem()
        val addToCartButton = dressesPage.addToCartButton()
        action.moveToElement(dressItem).build().perform()
        val dressDesc = dressesPage.dressDesc()
        dressDesc?.let { descList.add(it.text) }
        addToCartButton?.click()
    }



    @BeforeTest
    fun createDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/org/example/drivers/chromedriver.exe")
        driver = ChromeDriver()
    }

//
//    @AfterTest
//    fun tearDownDriver(){
//        driver?.quit()
//    }
}