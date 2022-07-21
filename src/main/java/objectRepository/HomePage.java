package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Reusables;

public class HomePage extends Reusables {

	public WebDriver driver;
	
	@FindBy(name = "mobile")
	private WebElement numeroDeCelular;

	@FindBy(name = "operator")
	private WebElement operador;

	@FindBy(xpath = "//b[text()='Telcel']")
	private WebElement operadorTelcel;

	@FindBy(xpath = "//div[text()='Recarga $10']")
	private WebElement montoDeRecarga10Dollar;

	@FindBy(xpath = "(//button[@class='button buttonRecharge'])[1]")
	private WebElement siguiente;

	public WebElement numeroDeCelular() {
		return numeroDeCelular;
	}

	public WebElement operador() {
		return operador;
	}

	public WebElement operadorTelcel() {
		return operadorTelcel;
	}

	public WebElement montoDeRecarga10Dollar() {
		return montoDeRecarga10Dollar;
	}

	public WebElement siguiente() {
		return siguiente;
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}