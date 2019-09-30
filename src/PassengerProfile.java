import java.util.ArrayList;
import java.util.function.Function;

import javafx.geometry.Insets;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class PassengerProfile {
	private PassengerGUI passengergui;
	private Stage stage;
	private Scene scene;
	private Passenger passenger;
	private ArrayList<Trip> passengerList = new ArrayList<Trip>();
	private TableView<Trip> table = new TableView<>();
	private ArrayList<Trip>totalList = new ArrayList<>();
	DataBase data = new DataBase();
	private int profileTableIndex;
	public PassengerProfile(Stage stage)
	{
		this.stage = stage;
	}
	public void prepareScene() {
		   table.getColumns().add(column("Source",Trip::getSource));
	       table.getColumns().add(column("Destination",Trip::getDestination));
	       table.getColumns().add(column("Tickets",Trip::getNoOfTickets));
	       table.getColumns().add(column("Departure Time",Trip::getDepTime));
	       table.getColumns().add(column("Arrival Time",Trip::getArrTime));
	       table.getColumns().add(column("Number of stops",Trip::getStops));
	       table.getColumns().add(column("Type",Trip::getType));
	       
	       
	       Text name = new Text("Welcome Passenger");
	       name.setFont(Font.font("Bold",16));
	       Text fill = new Text();
	       
	       Button remove = new Button("Remove");
	       Button load = new Button("Load");
	       Button select = new Button("Select");
	       Button back = new Button("Back");
	     	     
	       remove.setMinWidth(70);
	       load.setMinWidth(70);
	       select.setMinWidth(70);
	       back.setMinWidth(70);
	       
	       VBox vbText = new VBox();
	       vbText.getChildren().addAll(name,fill);
	       
	       VBox vbButton = new VBox(5);
	       vbButton.getChildren().addAll(load,back);
	       
	       VBox vbButton2 = new VBox(5);
	       vbButton2.getChildren().addAll(select,remove);
	       
	       HBox hb2 = new HBox(600);
	       hb2.setPadding(new Insets(0,10,10,10));
	       hb2.getChildren().addAll(vbButton,vbButton2);
	       
	       VBox vb = new VBox(30);
	       vb.setPadding(new Insets(10,10,10,10));
	        vb.getChildren().addAll(vbText,hb2,table);
	        scene = new Scene(vb);
	        load.setOnAction(e->{
	        	totalList=data.readPassengerFile(passenger);
			    deleteDuplicate(passengerList);
				refreshTable();
			;});
	        back.setOnAction(e->{
	        	select.setDisable(false);
	        stage.setScene(passengergui.getScene());	
	        ;});
	        select.setOnAction(e->{
				setTableIndex(table.getSelectionModel().selectedIndexProperty().get());
			 	select.setDisable(true);
			 	refreshTable();
			;});
	       remove.setOnAction(e->{
	    	   if(select.isDisabled())
	    	   {
	    		   int z=getTableIndex();
	   	    	totalList.remove(z);
	   	    	data.writePassengerFile(passenger,totalList);
	   	    	Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Information");
				alert.setHeaderText("Trip Removed Successfully But There's No Refund!");
				alert.showAndWait();
				
	    	   }
	    	   else
	    	   {
	    		   Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information");
					alert.setHeaderText("Please Select A Trip!");
					alert.showAndWait();
	    	   }
	    	
	    	   
	    	   refreshTable();
	       ;});
	}
	public Scene getScene() {
		return scene;
	}
	public void setPassengergui(PassengerGUI passengergui) {
		this.passengergui = passengergui;
	}
	public int getTableIndex() {
		return profileTableIndex;
	}
	public void setTableIndex(int profileTableIndex) {
		this.profileTableIndex = profileTableIndex;
	}
	public void refreshTable() {
		
		totalList.clear();
		table.setItems(FXCollections.observableList(data.readPassengerFile(passenger)));
	}
	private static <S,T> TableColumn<S,T> column(String title, Function<S,T> property) {
	    TableColumn<S,T> col = new TableColumn<>(title);
	    col.setMinWidth(100);
	    col.setCellValueFactory(cellData -> new ObservableValueBase<T>() {
	        @Override
	        public T getValue() {
	            return property.apply(cellData.getValue());
	        };
	    });
	    return col ;
	}
	public void deleteDuplicate(ArrayList<Trip> list)
	  {
		PassengerDataBase passengerDataBase=new PassengerDataBase();
		  for(int i=0; i<list.size();i++ )
			{
				for(int j =0; j < list.size();j++)
				{
					
					if((list.get(i).getTripNo()).equals(list.get(j).getTripNo())&(i!=j))
					{   
						int noOfTickets1=list.get(i).getNoOfTickets();
						int noOfTickets2=list.get(j).getNoOfTickets();
						list.get(i).setNoOfTickets(noOfTickets1+noOfTickets2);
						list.remove(j);
						passengerDataBase.writePassengerFile(passenger, list);
							
					}
				}
			}
	  }
	public Passenger getProfile()
	{
		return this.passenger;
	}
}
