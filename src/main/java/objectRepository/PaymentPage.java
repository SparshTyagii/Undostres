package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

	public WebDriver driver;

	@FindBy(className = "summary-message-top")
	private WebElement resumenDeLaCompra;

	@FindBy(xpath = "//p[text()='Tarjeta']")
	private WebElement tarjeta;

	@FindBy(xpath = "//label[@for='radio-c']")
	private WebElement usarNuevaTarjeta;

	@FindBy(id = "cardnumberunique")
	private WebElement numeroDeTarjeta;

	@FindBy(xpath = "//input[@placeholder='Mes']")
	private WebElement mes;

	@FindBy(xpath = "//input[@placeholder='Año']")
	private WebElement ano;

	@FindBy(xpath = "//input[@placeholder='CVV']")
	private WebElement cvv;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement correoElectronico;

	@FindBy(id = "paylimit")
	private WebElement pagarConTarjeta;

	@FindBy(id = "usrname")
	private WebElement correoElectronicoTelefonoMovil;

	@FindBy(name = "password")
	private WebElement contrasena;

	@FindBy(xpath = "//iframe[@title='reCAPTCHA']")
	private WebElement captchaFrame;

	@FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
	private WebElement captcha;

	@FindBy(xpath = "(//*[text()='Acceso'])[2]")
	private WebElement acceso;

	public WebElement resumenDeLaCompra() {
		return resumenDeLaCompra;
	}

	public WebElement tarjeta() {
		return tarjeta;
	}

	public WebElement usarNuevaTarjeta() {
		return usarNuevaTarjeta;
	}

	public WebElement numeroDeTarjeta() {
		return numeroDeTarjeta;
	}

	public WebElement mes() {
		return mes;
	}

	public WebElement ano() {
		return ano;
	}

	public WebElement cvv() {
		return cvv;
	}

	public WebElement correoElectronico() {
		return correoElectronico;
	}

	public WebElement pagarConTarjeta() {
		return pagarConTarjeta;
	}

	public WebElement correoElectronicoTelefonoMovil() {
		return correoElectronicoTelefonoMovil;
	}

	public WebElement contrasena() {
		return contrasena;
	}

	public WebElement captchaFrame() {
		return captchaFrame;
	}

	public WebElement captcha() {
		return captcha;
	}

	public WebElement acceso() {
		return acceso;
	}

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}