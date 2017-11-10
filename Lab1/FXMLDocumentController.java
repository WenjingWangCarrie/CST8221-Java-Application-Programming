package example6;

/* CST8221-JAP: LAB 01, Example 6SFXCSS
File name: FXMLDocumentController.java
*/

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * This class implements the controller responsible for handling the GUI events.
 *
 * @author Svillen Ranev
 * @version 1.17.2
 * @since JavaFX 8.0
 */
public class FXMLDocumentController implements Initializable {
	// link FXML description with components
	@FXML
	private Label label;
	private boolean again;

	// implement the handler
	@FXML
	private void handleButtonAction(ActionEvent event) {
		if (!again) {
			label.setText("Why did you do that?");
			again = true;
		} else {
			label.setText("Not Again!!!");
			again = false;
		}
	}

	// Called to initialize a controller after its root element has been
	// completely processed.
	// The initialize method is called after all @FXML annotated members have
	// been injected.
	// The controller constructor does not have access to the those members.
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		label.setText("Green Label");
	}

}
