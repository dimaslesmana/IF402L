package application;

import java.util.List;
import java.util.NoSuchElementException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaleFormController {
	@FXML
	private ComboBox<String> cboItem;

	@FXML
	private TextField txtSellingPrice, txtAmount;

	private User user;
	private List<Item> listOfItem;

	public void setSaleFormData(User user, List<Item> listOfItem) {
		this.user = user;
		this.listOfItem = listOfItem;
		listOfItem.stream().forEach(item -> cboItem.getItems().add(item.getName()));
	}

	public void addSale() {
		String title = "Warning Dialog";

		try {
			String selectedItemName = cboItem.getSelectionModel().getSelectedItem();
			Item item = listOfItem.stream()
					.filter(i -> i.getName().equals(selectedItemName))
					.findFirst()
					.get();

			Integer amount = Integer.parseInt(txtAmount.getText());
			Integer price = Integer.parseInt(txtSellingPrice.getText());
			Sale sale = new Sale(item, amount, price);
			user.addTransaction(sale);

			Stage stage = (Stage)cboItem.getScene().getWindow();
			stage.close();
		} catch (NoSuchElementException e) {
			String header = "No Selected item";
			String content = "Please select item from the item options";
			presentAlert(title, header, content);
		} catch (NumberFormatException e) {
			String header = "Price and amount must be a number!";
			String content = "Please input number for the price and amount field";
			presentAlert(title, header, content);
		}
	}

	private void presentAlert(String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

}
