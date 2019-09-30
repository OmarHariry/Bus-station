
import javafx.application.Application;
import javafx.stage.Stage;
public class BusStation extends Application {
	public static void main(String args[]) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
			Login login = new Login(primaryStage);
			UserChoice userchoice = new UserChoice(primaryStage);
			PassengerGUI passengergui = new PassengerGUI(primaryStage);
			ManagerHome managerHome = new ManagerHome(primaryStage);
			PassengerProfile profile = new PassengerProfile(primaryStage);
			DriverGUI driver = new DriverGUI(primaryStage);
			primaryStage.setTitle(" Bus  Station ");
			login.prepareScene();
			userchoice.prepareScene();
			passengergui.prepareScene();
			managerHome.prepareScene();
		    profile.prepareScene();
			driver.prepareScene();
			login.setManagerHome(managerHome);
			managerHome.setLogin(login);
			login.setUserchoice(userchoice);
			userchoice.setLogin(login);
			login.setDriver(driver);
			driver.setLogin(login);
			login.setPassenger(passengergui);
			passengergui.setLogin(login);
			passengergui.setProfile(profile);
			profile.setPassengergui(passengergui);
		primaryStage.setScene(userchoice.getScene());
		primaryStage.show();
	}
}