package testScript;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import objectRepository.HomePage;
import objectRepository.PaymentPage;
import utilities.Reusables;

public class RechargeTest extends Reusables {

	ExtentTest test;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		extentSetup();
	}

	@Test(priority = 0)
	public void homePage() throws Exception {

		test = extentCreateTest("Home Page");

		browserLaunch();

		HomePage h = new HomePage(driver);

		driver.get(properties.getProperty("url"));

		click(h.operador(), "Operador");
		click(h.operadorTelcel(), "Telcel");
		click(h.montoDeRecarga10Dollar(), "$10");
		sendKeys(h.numeroDeCelular(), "Numero de celular", "8465433546");
		click(h.siguiente(), "Siguiente");
	}

	@Test(priority = 1)
	public void paymentPage() throws Exception {

		test = extentCreateTest("Payment Page");

		PaymentPage p = new PaymentPage(driver);

		verifyText(p.resumenDeLaCompra(), "Resumen de la compra");
		click(p.tarjeta(), "Tarjeta");

		Thread.sleep(500);

		click(p.usarNuevaTarjeta(), "Usar nueva tarjeta");
		sendKeys(p.numeroDeTarjeta(), "Numero de tarjeta", "4111111111111111");
		sendKeys(p.mes(), "Mes", "11");
		sendKeys(p.ano(), "Ano", "25");
		sendKeys(p.cvv(), "CVV", "111");
		sendKeys(p.correoElectronico(), "Correo electronico", "test@test.com");
		click(p.pagarConTarjeta(), "Pagar con Tarjeta");
		sendKeys(p.correoElectronicoTelefonoMovil(), " Correo Electrónico / Teléfono móvil",
				"automationexcersise@test.com");
		sendKeys(p.contrasena(), "Contrasena", "123456");
		scrollToElement(p.acceso(), "Accesso");
		switchToFrame(p.captchaFrame(), "Captcha");
		click(p.captcha(), "Checkbox");
		switchToParentFrame();
		click(p.acceso(), "Acceso");

		if (p.acceso().isDisplayed())
			test.info("Payment Confirmation Screen is not Appearing due to captcha");
		else
			test.info("RechargeTest Successful");
	}

	@AfterSuite
	public void tearDown() throws Exception {
		extentFlush();
		extentReportOpen();
	}
}