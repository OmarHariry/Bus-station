
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
public class Login {
	Scene scene;
	Stage stage;
	PassengerGUI passengergui;
	UserChoice userchoice;
	String who;
	ManagerHome managerHome;
	DataBase data;
	DriverGUI drivergui;
	Driver driver=new Driver();;
	
	private String nameOfDriver;
	public String getNameOfDriver() {
		return nameOfDriver;
	}
	public void setNameOfDriver(String nameOfDriver) {
		this.nameOfDriver = nameOfDriver;
	}
public Login(Stage stage) {
		this.stage = stage;
	}
   public Login() {
	}
	public void prepareScene() {
		DataBase data = new DataBase();
		TextField usernameTextField = new TextField();
		PasswordField password = new PasswordField();
		Text usernameText = new Text("Enter Username");
		Text pwText = new Text("Password");
		
		Button submit = new Button("Submit");
		Button back = new Button("Back");
	
		submit.setMinWidth(60);
		back.setMinWidth(60);
	
		
		VBox vb = new VBox(10);
		vb.getChildren().addAll(usernameText,usernameTextField,pwText,password);
		
		VBox vb2 = new VBox(30);
		vb2.getChildren().addAll(submit,back);
		
		HBox hb = new HBox(10);
		hb.getChildren().addAll(vb2);
		
		VBox vbTotal = new VBox(10);
		vbTotal.setPadding(new Insets(10,10,10,10));
		vbTotal.getChildren().addAll(vb,hb);
		
		
		
		
		submit.setOnAction(e->{
			if(who.equals("passenger"))
			{
				if(usernameTextField.getText().isEmpty()|| password.getText().isEmpty())
				{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Input!");
					alert.setHeaderText("Please Fill All The Fields!");
					alert.showAndWait();
				}
				else
				{
					if(data.confirmPassengerInfo(usernameTextField.getText(), password.getText()))
					{
		
						stage.setMaximized(true);
						stage.setScene(passengergui.getScene());

						usernameTextField.clear();
						password.clear();
					}
					else
					{
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Invalid Input!");
						alert.setHeaderText("Invalid Username Or Password!");
						alert.showAndWait();
					}
				}
				
			}
			
			else if ( who.equals("manager")) {
				if(usernameTextField.getText().equals("Omar")&&password.getText().equals("1234"))
				{
					stage.setMaximized(true);
					stage.setScene(managerHome.getScene());
					usernameTextField.clear();
					password.clear();
			}else
				{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Invalid Input!");
				alert.setHeaderText("Invalid Username Or Password!");
				alert.showAndWait();
				}
			
			}else if (who.contentEquals("driver")) {
				if (data.confirmLogin(usernameTextField.getText(), password.getText())) {
					nameOfDriver=data.whichDriver(usernameTextField.getText(), password.getText(), driver);
					if (nameOfDriver.equals("Paul")) {
						stage.setMaximized(true);
						stage.setScene(drivergui.getScene1());
					}
					if (nameOfDriver.equals("Mark")) {
						stage.setMaximized(true);
						stage.setScene(drivergui.getScene2());
					}
					if (nameOfDriver.equals("John")) {
						stage.setMaximized(true);
						stage.setScene(drivergui.getScene3());
					}

				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Input!");
					alert.setHeaderText("Invalid Username Or Password!");
					alert.showAndWait();
				}
				usernameTextField.clear();
				password.clear();

			}
		});
		back.setOnAction(e -> {
			stage.setScene(userchoice.getScene());
			;
		});
        scene = new Scene(vbTotal);
       
	}
public Scene getScene() {
		return this.scene;
	}
	public void setUserchoice(UserChoice userchoice) {
		this.userchoice = userchoice;
	}
	public void setPassenger(PassengerGUI passengergui) {
		this.passengergui = passengergui;
	}
	public void setDriver(DriverGUI drivergui) {
		this.drivergui = drivergui;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public void setManagerHome(ManagerHome managerHome) {
		this.managerHome = managerHome;
	}
}

