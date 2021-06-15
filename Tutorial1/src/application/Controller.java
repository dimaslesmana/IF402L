package application;

//import java.net.URL;
//import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
	Clock clock;
	@FXML Label lblClock;

	public Controller() {
		clock = new Clock();
	}

	@FXML protected void handleBtnSecond(ActionEvent event) {
		clock.addSecond();
		updateLabel();
	}

	@FXML protected void handleBtnMinute(ActionEvent event) {
		clock.addMinute();;
		updateLabel();
	}

	@FXML protected void handleBtnHour(ActionEvent event) {
		clock.addHour();;
		updateLabel();
	}

	private void updateLabel() {
		lblClock.setText(clock.getTime());
	}

}
