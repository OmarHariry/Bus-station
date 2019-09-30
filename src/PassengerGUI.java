import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.util.ArrayList;
import java.util.function.Function;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tooltip;
public class PassengerGUI {

	Login login;
	Stage stage;
	Scene scene;
	Trip trip;
	PassengerProfile profile;
	PassengerGUI passengergui;
	Passenger passenger= new Passenger();
	private ArrayList<Trip> totalList = new ArrayList<Trip>();
	private ArrayList<Trip> passengerTrips = new ArrayList<Trip>();
	TableView<Trip> table = new TableView<>();
	DataBase database = new DataBase();
	private int maxSpinner;
	
	private int tableIndex;
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public PassengerGUI(Stage stage) {
		this.stage = stage;
	}
	
	DataBase data = new DataBase();
	PassengerDataBase passengerDataBase = new PassengerDataBase();
	
	public void prepareScene() {
		table.setItems(FXCollections.observableList(totalList));
		table.getColumns().add(column("Trip Number", Trip::getTripNo));
		table.getColumns().add(column("Source", Trip::getSource));
		table.getColumns().add(column("Destination", Trip::getDestination));
		table.getColumns().add(column("Tickets", Trip::getNoOfTickets));
		table.getColumns().add(column("Departure Time", Trip::getDepTime));
		table.getColumns().add(column("Arrival Time", Trip::getArrTime));
		table.getColumns().add(column("Number of stops", Trip::getStops));
		table.getColumns().add(column("Type", Trip::getType));
		table.getColumns().add(column("Vehicle", Trip::getVehicle));
		table.getColumns().add(column("Price", Trip::getPrice));
	
		Spinner<Integer> spinner = new Spinner<>();
		spinner.setTooltip(new Tooltip("Tickets"));
	
		//
		Text display = new Text("Welcome Passenger");
		Button profile1 = new Button("Profile");
		Button signOut = new Button("Sign Out");
		display.setFont(Font.font("Bold",16));
		profile1.setMinWidth(70);
		signOut.setMinWidth(70);
		
		VBox vbButtons = new VBox(10);
		vbButtons.getChildren().addAll(profile1,signOut);
		
		VBox vbTexts = new VBox(10);
		vbTexts.getChildren().addAll(display);
		
		HBox hbTop = new HBox(700);
		hbTop.setPadding(new Insets(0,5,5,5));
		hbTop.getChildren().addAll(vbTexts,vbButtons);
		
		Button submit = new Button("Submit");
		Button select = new Button("Select");
		Button load = new Button("Load");
		
		HBox hb1 = new HBox(10);
		hb1.getChildren().addAll(spinner);
	
		HBox hb3 = new HBox(10);
		hb3.getChildren().addAll(load, submit, select);
	
		VBox vb = new VBox(hb1, hb3);
		vb.setSpacing(10);
	
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(20, 20, 20, 20));
		bPane.setCenter(table);
		bPane.setTop(hbTop);
		bPane.setLeft(vb);
		load.requestFocus();
		submit.setOnAction(e -> {
			if (spinner.getValue() == null || spinner.getValue() == 0
					|| !select.isDisabled()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid input");
				alert.setHeaderText("Please Fill All The Fields");
				alert.setContentText("");
				alert.showAndWait();
			} else {
				trip = totalList.get(getTableIndex());
				int noOfTickets = spinner.getValue();
				passenger.setTrips(passenger.addTrip(trip, noOfTickets));
				data.writePassengerFile(passenger, passenger.getTrips());
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Trip Added Successfully");
				alert.setHeaderText("Trip added");
				alert.setContentText("Check your profile");
				alert.showAndWait();
				refreshTable();
				int totalNoOfTickets = totalList.get(getTableIndex())
						.getNoOfTickets();
				totalList.get(getTableIndex()).setNoOfTickets(
						totalNoOfTickets - noOfTickets);
				spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
						0, maxSpinner - noOfTickets));

				if (noOfTickets == totalNoOfTickets) {
					totalList.remove(getTableIndex());
					database.writeFile(totalList);
					refreshTable();
				}
				if (noOfTickets > 0) {
					database.writeFile(totalList);
					select.setDisable(false);
				} else {
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("Please Enter Number Of Tickets");
					alert2.setHeaderText("Please Enter Number Of Tickets");
					alert2.showAndWait();
				}
			}
			;
		});
		
	
		profile1.setOnAction(e -> {
			stage.setMaximized(true);
			stage.setScene(profile.getScene());

			;
		});
		select.setOnAction(e -> {
			setTableIndex(table.getSelectionModel().selectedIndexProperty()
					.get());
			maxSpinner = totalList.get(getTableIndex()).getNoOfTickets();
			select.setDisable(true);
			spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
					0, maxSpinner));

			refreshTable();
			;
		});

		signOut.setOnAction(e -> {
			stage.setScene(login.getScene());
			;
		});

		load.setOnAction(e -> {

			totalList = database.readTripFile();
	
			refreshTable();

			;
		});
		scene = new Scene(bPane);
	}

	public Scene getScene() {
		return this.scene;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	private static <S, T> TableColumn<S, T> column(String title,
			Function<S, T> property) {
		TableColumn<S, T> col = new TableColumn<>(title);
		col.setCellValueFactory(cellData -> new ObservableValueBase<T>() {
			@Override
			public T getValue() {
				return property.apply(cellData.getValue());
			};
		});
		return col;
	}
	public int getTableIndex() {
		return tableIndex;
	}
	public void setTableIndex(int tableIndex) {
		this.tableIndex = tableIndex;
	}
	public void refreshTable() {
		totalList.clear();
		table.setItems(FXCollections.observableList(database.readTripFile()));
	}
	public void setProfile(PassengerProfile profile)
	{
		this.profile=profile;
	}
	public Passenger getProfile()
	{
		return this.passenger;
	}
}




