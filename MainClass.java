import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainClass extends Application {
	String orderData = "";
	
	public static void main(String[] args){
		
		int orderCount = 0;
		
		ProductExtraction pe = new ProductExtraction();
		pe.setOrderCount(orderCount);
		
		launch(args);
		
	}

	@Override
	public void start(Stage firstStage) throws Exception {
		
		ImageView img = new ImageView("file:///D:/Shopping_Cart/logo2.png");
		img.setFitHeight(200);
		img.setFitWidth(300);
		
		Label email = new Label("Email");
		TextField mailField = new TextField();
		mailField.setPromptText("someone@example.com");
		
		Label pass = new Label("Password");
		PasswordField passField = new PasswordField();
		passField.setPromptText("Password");
		
		Button loginButton = new Button("Log In");
		Button createButton = new Button("Create Account");
		
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(createButton, loginButton);
		buttons.setAlignment(Pos.CENTER);
		
		GridPane gp1 = new GridPane();
		
		gp1.setHgap(20);
		gp1.setVgap(30);
		
		gp1.setAlignment(Pos.CENTER);
		
		gp1.add(img, 0, 0, 2, 1);
		
		gp1.add(email, 0, 1);
		gp1.add(mailField, 1, 1);
		
		gp1.add(pass, 0, 2);
		gp1.add(passField, 1, 2);
		
		gp1.add(buttons, 0, 3, 2, 1);
		
		Scene login = new Scene(gp1, 1000, 650);
		firstStage.setScene(login);
		firstStage.show();
		
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				if (((mailField.getText().contentEquals(".\\admin")) || (mailField.getText().contentEquals("admin@gmail.com"))) && (passField.getText().contentEquals(".\\admin"))){
					Alert a = new Alert(AlertType.INFORMATION);
					a.setContentText("Welcome");
					a.showAndWait();
					
					//admin product constructor
					
					AdminPanel ap = new AdminPanel(firstStage);
					
					mailField.setText(null);
					passField.setText(null);
				}
				else {
					String mail = mailField.getText();
					String passcode = passField.getText();
					
					LoginValidation fr = new LoginValidation();
					boolean stts = fr.findRecord(mail, passcode, orderData);
					orderData = fr.returnOrderData();
					if(stts == true){
						//products class constructor
						ProductCatalog catalog = new ProductCatalog(firstStage, orderData);
						
						mailField.setText(null);
						passField.setText(null);
					}
					else{
						Alert noUser = new Alert(AlertType.ERROR);
						noUser.setContentText("INVALID email or password!");
						noUser.show();
					}
				}
			}
		});
		
		createButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				SignUp sign = new SignUp(firstStage);
			}
		});
		
	}

}
