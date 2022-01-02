package fr.ensisa.hassenforder.brittle.management.view;

import java.util.List;

import fr.ensisa.hassenforder.brittle.management.model.Card;
import fr.ensisa.hassenforder.brittle.management.network.ISession;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagementController {

	private Card card = new Card();
	private ObservableList<Card> cards = FXCollections.observableArrayList();
	private ISession session;

	private StringProperty status;
	private StringProperty name;
	private StringProperty transfer;

	@FXML
    private Parent pane;
	@FXML
	private Label statusLabel;
	@FXML
	private TextField nameField;
	@FXML
	private TextField transferField;
	@FXML
	private Button depositButton;
	@FXML
	private Button withdrawButton;
    @FXML
    private TableView<Card> cardTable;
    @FXML
    private TableColumn<Card, Long> tableNumberColumn;
    @FXML
    private TableColumn<Card, String>  tableOwnerColumn;
    @FXML
    private TableColumn<Card, Long>  tableAmountColumn;
    @FXML
    private TableColumn<Card, Boolean>  tableValidColumn;

	@FXML
	private Label cardNumber;
	@FXML
	private Label cardCCV;
	@FXML
	private Label cardOwner;
	@FXML
	private Label cardAmount;
	@FXML
	private CheckBox cardValid;

	private void updateStatus (String text) {
    	status.set(text);
    }

    private long convertLong (String text) {
    	if (text == null) return 0;
    	try {
        	long value = Long.parseLong(text);
    		return value;
		}
    	catch (Exception e) {
    		return 0;
    	}
    }

    @FXML
    private void initialize() {
    	status = new SimpleStringProperty();
    	statusLabel.textProperty().bind(status);

    	name = new SimpleStringProperty();
    	nameField.textProperty().bindBidirectional(name);

    	transfer = new SimpleStringProperty();
    	transferField.textProperty().bindBidirectional(transfer);

    	cardNumber.textProperty().bind(card.getNumber().asString());
    	cardCCV.textProperty().bind(card.getCcv().asString());
    	cardOwner.textProperty().bind(card.getOwner());
    	cardAmount.textProperty().bind(card.getAmount().asString());
    	cardValid.disableProperty().bind(card.getValid().not());
    	cardValid.selectedProperty().bindBidirectional(card.getValid());

    	depositButton.disableProperty().bind (Bindings.or(
    			Bindings.equal(0, card.getNumber()),
    			card.getValid().not()
			));
    	withdrawButton.disableProperty().bind (Bindings.or(
    			Bindings.equal(0, card.getNumber()),
    			card.getValid().not()
			));

    	cardTable.setItems(cards);
    	cardTable.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> cardSelected (newValue)
    		);
    	tableNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumber().asObject());
    	tableOwnerColumn.setCellValueFactory(cellData -> cellData.getValue().getOwner());
    	tableAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getAmount().asObject());
    	tableValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValid().asObject());

    	status.set("Ready");
	}

    private void updateCard (long number) {
    	if (number != 0) {
        	updateStatus("Getting card ");
	    	Card card = session.getCard(number);
	    	if (card != null) {
	        	this.card.setWith(card);
	    		updateStatus("Getting successfull");
	    	} else {
	    		updateStatus("Getting wrong");
	    	}
    	} else {
        	this.card.setWith(null);
    	}
    }

    private void cardSelected(Card newCard) {
		if (newCard != null) {
			updateCard (newCard.getNumber().get());
		}
    }

    @FXML
    private void onClear() {
    	this.card.setWith(null);
    }

    @FXML
    private void onCreate() {
    	updateStatus("Create card "+name);
    	Long number= session.createCard(name.get());
    	if (number != null) {
        	if (number != 0) {
        		updateStatus("Creation successfull");
        		updateCard (number);
        	} else {
        		updateStatus("Creation failed");
        	}
    	} else {
    		updateStatus("Creation wrong");
    	}
    }

    @FXML
    private void onDeposit() {
    	updateStatus("Deposit of "+transfer);
    	long tranferAmount = convertLong(transfer.get());
    	if (tranferAmount != 0) {
    		long number = card.getNumber().get();
	    	Boolean result = session.depositTo(number, tranferAmount);
	    	if (result != null) {
	        	if (result == Boolean.TRUE) {
	            	updateStatus("Deposit sucessfull");
	            	updateCard(number);
            	} else {
	        		updateStatus("Deposit failed");
	        	}
	    	} else {
	    		updateStatus("Deposit goes wrong");
	    	}
    	} else {
    		updateStatus("Deposit 0 is not allowed");
    	}
    }

    @FXML
    private void onWithdraw() {
    	updateStatus("Withdraw of "+transfer);
    	long tranferAmount = convertLong(transfer.get());
    	if (tranferAmount != 0) {
    		long number = card.getNumber().get();
	    	Boolean result = session.withdrawFrom(number, tranferAmount);
	    	if (result != null) {
	        	if (result == Boolean.TRUE) {
	            	updateStatus("Withdraw sucessfull");
	            	updateCard(number);
	        	} else {
	        		updateStatus("Withdraw failed");
	        	}
	    	} else {
	    		updateStatus("Withdraw goes wrong");
	    	}
	    } else {
    		updateStatus("Withdraw 0 is not allowed");
	    }
    }

    @FXML
    private void onValidChanged() {
    	updateStatus("Start invalidation of the card");
    	Boolean result = session.invalidate(card.getNumber().get());
    	if (result != null) {
        	if (result == Boolean.TRUE) {
            	updateStatus("Card invalidated");
        	} else {
        		updateStatus("Invalidation failed");
        	}
    	} else {
    		updateStatus("Invalidation goes wrong");
    	}
    }

    @FXML
    private void onList() {
    	updateStatus("Start a list of cards");
    	List<Card> all = session.getAllCards();
    	if (all != null) {
    		cards.clear();
    		cards.addAll(all);
        	updateStatus("End of a list of cards");
    	} else {
    		updateStatus("Get list failed");
    	}
    }

    @FXML
    private void onPopulate() {
    	updateStatus("Populates");
    	Boolean result = session.populateCards();
    	if (result != null) {
        	if (result == Boolean.TRUE) {
        		updateStatus("Populates successfull");
        	} else {
        		updateStatus("Populates failed");
        	}
    	} else {
    		updateStatus("Populates wrong");
    	}
    }

	public void setSession(ISession session) {
		this.session = session;
	}

}
