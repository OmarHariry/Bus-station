
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class UserChoice {
  Scene scene;
  Stage stage;
  Login login;

public UserChoice(Stage stage) {
	  this.stage = stage;
  }
  public void prepareScene() {
	  Button employee=new Button("Employee");
		Button passenger=new Button("Passenger");
		Button manager=new Button("Manager");
		Button driver=new Button("Driver");
		Text display=new Text("Welcome To Our Bus Station!");
		display.setFont(Font.font("Bold",16));
		
		BorderPane bPane=new BorderPane();
		HBox hb=new HBox(187);
		
		hb.getChildren().addAll(manager,driver);
		bPane.setLeft(employee);
		bPane.setRight(passenger);
		bPane.setTop(display);
		bPane.setBottom(hb);
		hb.setVisible(false);
		employee.setOnAction(e->{
			employee.setDisable(true);
			passenger.setDisable(true);
			hb.setVisible(true);
		;});
		manager.setOnAction(e->{
			
			stage.setScene(login.getScene());
			login.setWho("manager");
			employee.setDisable(false);
			passenger.setDisable(false);
			hb.setVisible(false);
		});
		passenger.setOnAction(e->{
			stage.setScene(login.getScene());
			login.setWho("passenger");
			employee.setDisable(false);
			passenger.setDisable(false);
			
		});
		driver.setOnAction(e->{
			stage.setScene(login.getScene());
			login.setWho("driver");
			employee.setDisable(false);
			passenger.setDisable(false);
			hb.setVisible(false);
		});
		 scene =new Scene(bPane,300,300);
  }
  public Scene getScene() {
	  return this.scene;
  }
  public void setLogin(Login login) {
	this.login = login;
}
}
