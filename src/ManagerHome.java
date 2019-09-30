import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValueBase;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.function.Function;
import javafx.scene.text.Font;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;


public class ManagerHome  {

   Scene scene;
   Stage stage;
	private ArrayList<Trip> list = new ArrayList<>();
	private TableView<Trip> table;
	DataBase data = new DataBase();
	Vehicle obj = new Vehicle();
	Login login;
	private int index;
	public ManagerHome(Stage stage) {
		this.stage=stage;
	}
	public void prepareScene() {

		list = data.readTripFile();

		ComboBox<String> driver = new ComboBox<>();
		driver.getItems().addAll("Pogba", "Mark", "John");
		driver.setPromptText("Driver");
		
		ComboBox<String> vehicle = new ComboBox<>();
		vehicle.getItems().addAll("Limousine", "Bus", "Micro-Bus");
		vehicle.setPromptText("Vehicle");

		Text type = new Text("Type");
		Text header = new Text("Fill The Following Fields To Add A Trip!");
		header.setFont(Font.font("Bold",16));
		
		Button submit = new Button("Submit");
		Button signOut = new Button("Sign Out");
		Button delete = new Button("Delete");
		Button load = new Button("Load");
		Button edit = new Button("Edit");

		TextField fromTextField = new TextField("");
		TextField toTextField = new TextField("");
		TextField ticketsTextField = new TextField("");
		TextField depTimeTextField = new TextField("");
		TextField arrTimeTextField = new TextField("");
		TextField stopsTextField = new TextField("");
		TextField priceTextField = new TextField("");

		RadioButton oneway = new RadioButton("One-way trip");
		RadioButton roundTrip = new RadioButton("Round trip");
		ToggleGroup radioGroup = new ToggleGroup();

		oneway.setToggleGroup(radioGroup);
		roundTrip.setToggleGroup(radioGroup);

		HBox hb = new HBox(10);
		hb.getChildren().addAll(type,oneway,roundTrip);
		
		HBox hbComboBox = new HBox(10);
		hbComboBox.getChildren().addAll(driver,vehicle);
		
		HBox hb2 = new HBox(50);
		hb2.getChildren().addAll(load,delete,edit,submit);
		
		VBox vb = new VBox(100);
		vb.getChildren().addAll(hb2,signOut);
		
		VBox vbTotal = new VBox(15);
		vbTotal.getChildren().addAll(header,fromTextField, toTextField,
				ticketsTextField, depTimeTextField, arrTimeTextField,
				stopsTextField,priceTextField, hb, hbComboBox,vb);

		HBox hbTotal = new HBox();
		hbTotal.setPadding(new Insets(10,10,10,10));
		hbTotal.getChildren().addAll(vbTotal);

		submit.setOnAction(e -> {
			if (fromTextField.getText().isEmpty()
					|| toTextField.getText().isEmpty()
					|| ticketsTextField.getText().isEmpty()
					|| depTimeTextField.getText().isEmpty()
					|| arrTimeTextField.getText().isEmpty()
					|| stopsTextField.getText().isEmpty()
					|| priceTextField.getText().isEmpty()
					|| !(oneway.isSelected() | roundTrip.isSelected())
					|| driver.getSelectionModel().isEmpty()
					|| vehicle.getSelectionModel().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Invalid Input!");
				alert.setHeaderText("Please Fill All The Fields");
				alert.showAndWait();

			} else {
				if (fromTextField.getText().matches("\\d+(\\.\\d+)?")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Input!");
					alert.setHeaderText("Please Enter A Valid Source!");
					alert.showAndWait();
				}
				if (toTextField.getText().matches("\\d+(\\.\\d+)?")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Input!");
					alert.setHeaderText("Please Enter A Valid Destination");
					alert.showAndWait();
				}
				if (!ticketsTextField.getText().matches("^[1-9]\\d*$")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Input!");
					alert.setHeaderText("Please Enter A Valid Number Of Tickets!");
					alert.showAndWait();
				}
				if (!stopsTextField.getText().matches("^[0-9]\\d*$")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Input!");
					alert.setHeaderText("Please Enter A Valid Number Of Stops!");
					alert.showAndWait();
				} 
				if (!priceTextField.getText().matches("^[1-9]\\d*$")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Input!");
					alert.setHeaderText("Please Enter A Valid Price!");
					alert.showAndWait();
				}
				
				if(Integer.parseInt(depTimeTextField.getText())<1||Integer.valueOf(depTimeTextField.getText())>23||Integer.parseInt(arrTimeTextField.getText())<1||Integer.valueOf(arrTimeTextField.getText())>23)
				{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Input!");
					alert.setHeaderText("Please Enter A Valid Time");
					alert.showAndWait();
				}
				else {
					if(toTextField.getText().equalsIgnoreCase(fromTextField.getText()))
						{
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Invalid Input!");
							alert.setHeaderText("Please Enter A Valid Destination");
							alert.showAndWait();
						}
					else
					{
						Trip x = new Trip();
						x.setSource(fromTextField.getText());
						x.setDestination(toTextField.getText());
						x.setNoOfTickets(Integer.parseInt(ticketsTextField
								.getText()));
						x.setDepTime(depTimeTextField.getText());
						x.setArrTime(arrTimeTextField.getText());
						x.setStops(Integer.parseInt(stopsTextField.getText()));
						x.setPrice(Double.parseDouble(priceTextField.getText()));
						Boolean onewayBool = oneway.isSelected();
						if (onewayBool) {
							x.setType("One-Way Trip");
						} else {
							x.setType("Round Trip");
						}
						x.setDriver(driver.getValue());
						x.setVehicle(vehicle.getValue());
						if(x.getType().equals("One-Way Trip"))
						{
							if(x.getVehicle().equals("Limousine"))
							{
								obj.setTicketPrice(x.getPrice());
								x.setPrice(obj.getPrice("Limousine","onewaytrip"));
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Calculating Price!");
								alert.setHeaderText("The Trip Price Is :"+ x.getPrice() +"$");
								alert.showAndWait();
							}
							else if(x.getVehicle().equals("Bus"))
							{
								obj.setTicketPrice(x.getPrice());							
								x.setPrice(obj.getPrice("Bus","onewaytrip"));
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Calculating Price!");
								alert.setHeaderText("The Trip Price Is :"+ x.getPrice() +"$");
								alert.showAndWait();
							}
							else if(x.getVehicle().equals("Micro-Bus"))
							{
								obj.setTicketPrice(x.getPrice());
								x.setPrice(obj.getPrice("Micro-Bus","onewaytrip"));
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Calculating Price!");
								alert.setHeaderText("The Trip Price Is :"+ x.getPrice() +"$");
								alert.showAndWait();
							}
						}
						else if(x.getType().equals("Round Trip"))
						{
							if(x.getVehicle().equals("Limousine"))
							{
								obj.setTicketPrice(x.getPrice());
								x.setPrice(obj.getPrice("Limousine","roundtrip"));
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Calculating Price!");
								alert.setHeaderText("The Trip Price Is :"+ x.getPrice() +"$");
								alert.showAndWait();
							}
							
							
							else if(x.getVehicle().equals("Bus"))
							{
								obj.setTicketPrice(x.getPrice());
								x.setPrice(obj.getPrice("Bus","roundtrip"));
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Calculating Price!");
								alert.setHeaderText("The Trip Price Is :"+ x.getPrice() +"$");
								alert.showAndWait();
							}
							else if(x.getVehicle().equals("Micro-Bus"))
							{
								obj.setTicketPrice(x.getPrice());
								x.setPrice(obj.getPrice("Micro-Bus","onewaytrip"));
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Calculating Price!");
								alert.setHeaderText("The Trip Price Is :"+ x.getPrice() +"$");
								alert.showAndWait();
							}
						}
						if(edit.isDisable())
						{
							int index=getTableIndex();
							list.set(index, x);
							data.writeFile(list);
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Information");
							alert.setHeaderText("Trip Modified successfully!");
							alert.showAndWait();
						}
						else
						{
							list.add(x);
							data.writeFile(list);
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Information");
							alert.setHeaderText("Trip added successfully!");
							alert.showAndWait();
						}
						fromTextField.clear();
						toTextField.clear();
						ticketsTextField.clear();
						depTimeTextField.clear();
						arrTimeTextField.clear();
						stopsTextField.clear();
						priceTextField.clear();
						oneway.setSelected(false);
						roundTrip.setSelected(false);
						driver.setValue(null);
						vehicle.setValue(null);
						edit.setDisable(false);
						
						refreshTable();
						;
					}
				}

			}

			;
		});
		
		load.setOnAction(e->{
			list=data.readTripFile();
			refreshTable();
		;});
		edit.setOnAction(e->{
			fromTextField.requestFocus();
			edit.setDisable(true);
			setTableIndex(table.getSelectionModel().selectedIndexProperty().get());
			refreshTable();
		;});
		
		
		
		delete.setOnAction(e->{
			int index = table.getSelectionModel().selectedIndexProperty().get();
			list.remove(index);
			data.writeFile(list);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Trip Removed Successfully!");
			alert.showAndWait();
			refreshTable();
		;});
		
		
		signOut.setOnAction(e->{
			stage.setScene(login.getScene());
		});
		
		table = new TableView<>();
		
		table.getColumns().add(column("No.", Trip::getTripNo));
		table.getColumns().add(column("Source", Trip::getSource));
		table.getColumns().add(column("Destination", Trip::getDestination));
		table.getColumns().add(column("Tickets", Trip::getNoOfTickets));
		table.getColumns().add(column("Departure Time", Trip::getDepTime));
		table.getColumns().add(column("Arrival Time", Trip::getArrTime));
		table.getColumns().add(column("Stops", Trip::getStops));
		table.getColumns().add(column("Type", Trip::getType));
		table.getColumns().add(column("Driver", Trip::getDriver));
		table.getColumns().add(column("Vehicle", Trip::getVehicle));
		table.getColumns().add(column("Price", Trip::getPrice));
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(20,20,20,0));
		bPane.setLeft(hbTotal);
		bPane.setRight(table);
		 scene = new Scene(bPane);
		
		
		load.requestFocus();
		fromTextField.setPromptText("Source");
		toTextField.setPromptText("Destination");
		ticketsTextField.setPromptText("Number Of Tickets");
		depTimeTextField.setPromptText("Departure Time");
		arrTimeTextField.setPromptText("Arrival Time");
		stopsTextField.setPromptText("Number Of Stops");
		priceTextField.setPromptText("Price");
	}
	public void setTableIndex(int index)
	{
		this.index=index;
	}
	public int getTableIndex()
	{
		return index;
	}
	public void refreshTable() {
		list.clear();
		table.setItems(FXCollections.observableList(data.readTripFile()));
	}
	public Scene getScene() {
		return scene;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	private static <S, T> TableColumn<S, T> column(String title,
			Function<S, T> property) {
		TableColumn<S, T> col = new TableColumn<>(title);
		col.setMinWidth(50);
		col.setCellValueFactory(cellData -> new ObservableValueBase<T>() {
			@Override
			public T getValue() {
				return property.apply(cellData.getValue());
			};
		});
		return col;
	}
 
}