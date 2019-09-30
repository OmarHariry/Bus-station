
import java.util.ArrayList;
import java.util.function.Function;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class DriverGUI{
	DataBase data=new DataBase();
	Login login;
	Scene scene1;
	Scene scene2;
	Scene scene3;
	Stage stage;
	public DriverGUI(Stage stage){
		
		this.stage = stage;
}

	public void prepareScene( )
{	
	
	ArrayList<Trip>pogbaList=new ArrayList<>();
	ArrayList<Trip>markList=new ArrayList<>();
	ArrayList<Trip>johnList=new ArrayList<>();
	
	list=data.readTripFile();
	
	for(int i=0;i<list.size();i++)
	{
		if(list.get(i).getDriver().contains("Pogba"))
		{
			pogbaList.add(list.get(i));
		}
		if(list.get(i).getDriver().contains("Mark"))
		{
			markList.add(list.get(i));
		}
		if(list.get(i).getDriver().contains("John"))
		{
			johnList.add(list.get(i));
		}
	}
	Text id = new Text();
	Text firstName = new Text();
	Text lastName = new Text();
	Text username = new Text();
	Text pw = new Text();
	Text age = new Text();
	//
	Text display1 = new Text("Welcome Pogba,");
	Text text1 = new Text("Your Manager Has Assigned For You The Following Trips!");
	
	Button profile1 = new Button("Profile");
	Button signOut1 = new Button("Sign Out");
	display1.setFont(Font.font("Bold",16));
	text1.setFont(Font.font("Bold",16));
	profile1.setMinWidth(70);
	signOut1.setMinWidth(70);
	
	VBox vbButtons1 = new VBox(10);
	vbButtons1.getChildren().addAll(profile1,signOut1);
	
	VBox vbTexts1 = new VBox(10);
	vbTexts1.getChildren().addAll(display1,text1);
	
	HBox hb1 = new HBox(700);
	hb1.setPadding(new Insets(0,5,5,5));
	hb1.getChildren().addAll(vbTexts1,vbButtons1);
	
	profile1.setOnAction(e->{
		 id.setText(Integer.toString(data.readDriverFile().get(0).getID())); 
		 firstName.setText(data.readDriverFile().get(0).getFirstName());
		 lastName.setText(data.readDriverFile().get(0).getLastName());
		 username.setText(data.readDriverFile().get(0).getUsername());
		 pw.setText(data.readDriverFile().get(0).getPassword()); 
		 age.setText(Integer.toString(data.readDriverFile().get(0).getAge()));
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Your Personal Information");
		alert.setHeaderText("ID:"+ id.getText() +"\n"+ "First Name:"+ firstName.getText()+"\n"+"Last Name:"+ lastName.getText()+"\n"+"Age:"+ age.getText()+"\n"+"Username:"+ username.getText()+"\n"+"Password: "+ pw.getText());
		alert.showAndWait();
	;});
	signOut1.setOnAction(e->
	{
		stage.setScene(login.getScene());
	;});
	//
	Text display2 = new Text("Welcome Mark,");
	Text text2 = new Text("Your Manager Has Assigned For You The Following Trips!");
	
	Button profile2 = new Button("Profile");
	Button signOut2 = new Button("Sign Out");
	display2.setFont(Font.font("Bold",16));
	text2.setFont(Font.font("Bold",16));
	profile2.setMinWidth(70);
	signOut2.setMinWidth(70);
	
	VBox vbButtons2 = new VBox(10);
	vbButtons2.getChildren().addAll(profile2,signOut2);
	
	VBox vbTexts2 = new VBox(10);
	vbTexts2.getChildren().addAll(display2,text2);
	
	HBox hb2 = new HBox(700);
	hb2.setPadding(new Insets(5,5,5,5));
	hb2.getChildren().addAll(vbTexts2,vbButtons2);
	
	profile2.setOnAction(e->{
		 id.setText(Integer.toString(data.readDriverFile().get(1).getID())); 
		 firstName.setText(data.readDriverFile().get(1).getFirstName());
		 lastName.setText(data.readDriverFile().get(1).getLastName());
		 username.setText(data.readDriverFile().get(1).getUsername());
		 pw.setText(data.readDriverFile().get(1).getPassword()); 
		 age.setText(Integer.toString(data.readDriverFile().get(1).getAge()));
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Your Personal Information");
		alert.setHeaderText("ID:"+ id.getText() +"\n"+ "First Name:"+ firstName.getText()+"\n"+"Last Name:"+ lastName.getText()+"\n"+"Age:"+ age.getText()+"\n"+"Username:"+ username.getText()+"\n"+"Password: "+ pw.getText());
		alert.showAndWait();
	;});
	signOut2.setOnAction(e->
	{
		stage.setScene(login.getScene());
	;});
	//
	Text display3 = new Text("Welcome John,");
	Text text3 = new Text("Your Manager Has Assigned For You The Following Trips!");
	

	Button profile3 = new Button("Profile");
	Button signOut3 = new Button("Sign Out");
	profile3.setMinWidth(70);
	signOut3.setMinWidth(70);
	display3.setFont(Font.font("Bold",16));
	text3.setFont(Font.font("Bold",16));
	
	VBox vbButtons3 = new VBox(10);
	vbButtons3.getChildren().addAll(profile3,signOut3);
	
	VBox vbTexts3 = new VBox(10);
	vbTexts3.getChildren().addAll(display3,text3);
		
	HBox hb3 = new HBox(700);
	hb3.setPadding(new Insets(5,5,5,5));
	hb3.getChildren().addAll(display3,vbButtons3);
	
	profile3.setOnAction(e->{
		 id.setText(Integer.toString(data.readDriverFile().get(2).getID())); 
		 firstName.setText(data.readDriverFile().get(2).getFirstName());
		 lastName.setText(data.readDriverFile().get(2).getLastName());
		 username.setText(data.readDriverFile().get(2).getUsername());
		 pw.setText(data.readDriverFile().get(2).getPassword()); 
		 age.setText(Integer.toString(data.readDriverFile().get(2).getAge()));
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Your Personal Information");
		alert.setHeaderText("ID:"+ id.getText() +"\n"+ "First Name:"+ firstName.getText()+"\n"+"Last Name:"+ lastName.getText()+"\n"+"Age:"+ age.getText()+"\n"+"Username:"+ username.getText()+"\n"+"Password: "+ pw.getText());
		alert.showAndWait();
	;});
	signOut3.setOnAction(e->
	{
		stage.setScene(login.getScene());
	;});

	TableView<Trip> table1 = new TableView<>();
	table1.setItems(FXCollections.observableList((pogbaList)));
	table1.getColumns().add(column("Source", Trip::getSource));
	table1.getColumns().add(column("Destination", Trip::getDestination));
	table1.getColumns().add(column("Tickets", Trip::getNoOfTickets));
	table1.getColumns().add(column("Departure Time", Trip::getDepTime));
	table1.getColumns().add(column("Arrival Time", Trip::getArrTime));
	table1.getColumns().add(column("Stops", Trip::getStops));
	table1.getColumns().add(column("Type", Trip::getType));
	table1.getColumns().add(column("Driver", Trip::getDriver));
	
	
	TableView<Trip> table2 = new TableView<>();
	table2.setItems(FXCollections.observableList((markList)));
	table2.getColumns().add(column("Source", Trip::getSource));
	table2.getColumns().add(column("Destination", Trip::getDestination));
	table2.getColumns().add(column("Tickets", Trip::getNoOfTickets));
	table2.getColumns().add(column("Departure Time", Trip::getDepTime));
	table2.getColumns().add(column("Arrival Time", Trip::getArrTime));
	table2.getColumns().add(column("Stops", Trip::getStops));
	table2.getColumns().add(column("Type", Trip::getType));
	table2.getColumns().add(column("Driver", Trip::getDriver));
	
	
	TableView<Trip> table3 = new TableView<>();
	table3.setItems(FXCollections.observableList((johnList)));
	table3.getColumns().add(column("Source", Trip::getSource));
	table3.getColumns().add(column("Destination", Trip::getDestination));
	table3.getColumns().add(column("Tickets", Trip::getNoOfTickets));
	table3.getColumns().add(column("Departure Time", Trip::getDepTime));
	table3.getColumns().add(column("Arrival Time", Trip::getArrTime));
	table3.getColumns().add(column("Stops", Trip::getStops));
	table3.getColumns().add(column("Type", Trip::getType));
	table3.getColumns().add(column("Driver", Trip::getDriver));
	


	BorderPane bPane1 = new BorderPane();
	bPane1.setCenter(table1);
	bPane1.setTop(hb1);
	bPane1.setPadding(new Insets(10,10,10,10));
	
	
	BorderPane bPane2 = new BorderPane();
	bPane2.setCenter(table2);
	bPane2.setTop(hb2);
	bPane2.setPadding(new Insets(20,20,20,20));
	
	
	BorderPane bPane3 = new BorderPane();
	bPane3.setCenter(table3);
	bPane3.setTop(hb3);
	bPane3.setPadding(new Insets(20,20,20,20));
	
	 scene1 = new Scene(bPane1);
	 scene2 = new Scene(bPane2);
	 scene3 = new Scene(bPane3);
	
	
}
private static <S, T> TableColumn<S, T> column(String title,
		Function<S, T> property) {
	TableColumn<S, T> col = new TableColumn<>(title);
	col.setMinWidth(100);
	col.setCellValueFactory(cellData -> new ObservableValueBase<T>() {
		@Override
		public T getValue() {
			return property.apply(cellData.getValue());
		};
	});
	return col;
}
ArrayList<Trip>list=new ArrayList<>();
public Scene getScene1() {
	return scene1;
}

public Scene getScene2() {
	return scene2;
}

public Scene getScene3() {
	return scene3;
}
public void setLogin(Login login) {
	this.login = login;
}

}
