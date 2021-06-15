package application;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	@FXML
	public Pagination pagination;

	@FXML
	protected TextField txtFullName, txtGender, txtAddress, txtIncome;

	@FXML
	protected ListView<String> lvPurchase, lvSale;

	protected int contentPerPage = 7;

	protected String selectedName;
	protected User selectedUser;

	List<User> listOfUser = new ArrayList<>();
	List<Item> listOfItem = new ArrayList<>();

	public void seedUser() {
		listOfUser.add(new User("John", "Doe", 'M', "Dove Street"));
		listOfUser.add(new User("Van", "Doe", 'M', "Crow Street"));
		listOfUser.add(new User("Tom", "Doe", 'M', "Eagle Street"));
		listOfUser.add(new User("Chuan", "Doe", 'M', "Dove Street"));
		listOfUser.add(new User("Pan", "Doe", 'M', "Dove Street"));
		listOfUser.add(new User("San", "Doe", 'M', "Dove Street"));
		listOfUser.add(new User("Ran", "Doe", 'M', "Dove Street"));
		listOfUser.add(new User("Sam", "Doe", 'M', "Dove Street"));
	}

	public void seedItem() {
		listOfItem.add(new Item("Helm", 125000));
		listOfItem.add(new Item("Obeng", 12000));
		listOfItem.add(new Item("Spion", 18000));
		listOfItem.add(new Item("Oli", 30000));
	}

	@FXML
	public void initialize() {
		selectedName = "";

		seedUser();
		seedItem();

		double pageCount = (double)listOfUser.size() / contentPerPage;
		pageCount = Math.ceil(pageCount);

		pagination.setPageCount((int)pageCount);
		pagination.setPageFactory(param -> createPage(param));
	}

	public ListView<String> createPage(int pageIndex) {
		ListView<String> lvUser = new ListView<>();
		lvUser.setOnMouseClicked(event -> selectedName = lvUser.getSelectionModel().getSelectedItem());

		int minIndex = pageIndex * contentPerPage;
		int maxIndex = pageIndex+1 * contentPerPage;
		maxIndex = Math.min(maxIndex, listOfUser.size());

		LinkedList<String> listOfNames = listOfUser.subList(minIndex, maxIndex)
				.stream()
				.map(user->user.getFullName())
				.collect(Collectors.toCollection(LinkedList::new));

		ObservableList<String> items = FXCollections.observableArrayList(listOfNames);
		lvUser.setItems(items);

		return lvUser;
	}

	public void handleLoadInfo() {
		try {
			Optional<User> userOptional = listOfUser.stream()
					.filter(u -> u.getFullName().equals(selectedName))
					.findFirst();

			selectedUser = userOptional.get();
			txtFullName.setText(selectedUser.getFullName());
			txtGender.setText(selectedUser.getGender());
			txtAddress.setText(selectedUser.getAddress());

			refreshSaleListView();
			refreshPurchaseListView();
			refreshIncome();
		} catch (NoSuchElementException ex) {
			System.out.println("No Selected element");
		}
	}

	public void handlePurchase() {
		if (selectedUser == null) {
			String title = "Warning";
			String header = "No Selected user";
			String content = "Please select user from the list";
			presentAlert(title, header, content);

			return;
		}

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PurchaseForm.fxml"));
			Parent root = loader.load();
			Stage purchaseStage = new Stage();

			purchaseStage.initModality(Modality.APPLICATION_MODAL);
			purchaseStage.setTitle("Purchase");
			purchaseStage.setResizable(false);
			purchaseStage.setScene(new Scene(root));

			PurchaseFormController controller = loader.getController();
			controller.setPurchaseFormData(selectedUser, listOfItem);
			purchaseStage.showAndWait();

			refreshPurchaseListView();
			refreshIncome();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to open form!");
		}
	}

	public void handleSale() {
		if (selectedUser == null) {
			String title = "Warning";
			String header = "No Selected user";
			String content = "Please select user from the list";
			presentAlert(title, header, content);

			return;
		}

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SaleForm.fxml"));
			Parent root = loader.load();
			Stage saleStage = new Stage();

			saleStage.initModality(Modality.APPLICATION_MODAL);
			saleStage.setTitle("Sale");
			saleStage.setResizable(false);
			saleStage.setScene(new Scene(root));

			SaleFormController controller = loader.getController();
			controller.setSaleFormData(selectedUser, listOfItem);
			saleStage.showAndWait();

			refreshSaleListView();
			refreshIncome();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to open form!");
		}
	}

	public void refreshPurchaseListView() {
		lvPurchase.getItems().clear();

		for (Transaction p : selectedUser.getPurchases()) {
			lvPurchase.getItems().add(p.getTransactionInfo());
		}
	}

	public void refreshSaleListView() {
		lvSale.getItems().clear();
		try {

			for (Transaction p : selectedUser.getSales()) {
				lvSale.getItems().add(p.getTransactionInfo());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void refreshIncome() {
		txtIncome.setText(selectedUser.getIncome().toString());
	}

	private void presentAlert(String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

}
