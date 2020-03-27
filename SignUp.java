

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class SignUp extends Application {
	
	SignUp(Stage secondStage){
		try {
			start(secondStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void start(Stage secondStage) throws Exception {
		
		ImageView img = new ImageView("file:///D:/Shopping_Cart/logo2.png");
		img.setFitHeight(200);
		img.setFitWidth(300);
		
		Label fullName = new Label("Full Name");
		TextField fullNameField = new TextField();
		fullNameField.setPromptText("Full Name");
		
		Label username = new Label("User Name");
		TextField usernameField = new TextField();
		usernameField.setPromptText("User Name");
		
		Label email = new Label("E-mail");
		TextField mailField = new TextField();
		mailField.setPromptText("example@email.com");
		
		Label pass1 = new Label("Password");
		PasswordField passField1 = new PasswordField();
		passField1.setPromptText("Password");
		
		Label pass2 = new Label("Re-enter Password");
		PasswordField passField2 = new PasswordField();
		passField2.setPromptText("Re-enter Password");
		
		Label ph = new Label("Phone#");
		TextField phField = new TextField();
		phField.setPromptText("03XX-XXXXXXX");
		
		Label add = new Label("Address");
		TextArea addField = new TextArea();
		addField.setMaxSize(200, 100);
		
		Button createButton = new Button("Create Account");
		Button back = new Button("Cancel");
		
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(back, createButton);
		buttons.setAlignment(Pos.CENTER);
		
		
		GridPane gp2 = new GridPane();
						
		gp2.setAlignment(Pos.CENTER);
		
		gp2.add(img, 0, 0, 2, 1);
		
		gp2.add(fullName, 0, 1);
		gp2.add(fullNameField, 1, 1);

		gp2.add(username, 0, 2);
		gp2.add(usernameField, 1, 2);

		gp2.add(email, 0, 3);
		gp2.add(mailField, 1, 3);

		gp2.add(pass1, 0, 4);
		gp2.add(passField1, 1, 4);

		gp2.add(pass2, 0, 5);
		gp2.add(passField2, 1, 5);

		gp2.add(ph, 0, 6);
		gp2.add(phField, 1, 6);

		gp2.add(add, 0, 7);
		gp2.add(addField, 1, 7);
		
		gp2.add(buttons, 0, 8, 2, 1);
		//gp2.add(back, 1, 8);
		
		GridPane.setHalignment(createButton, HPos.CENTER);
		GridPane.setHalignment(img, HPos.CENTER);
		
		
		
		gp2.setVgap(20);
		gp2.setHgap(20);
				
		Scene signup = new Scene(gp2, 1000, 650);
		secondStage.setScene(signup);
		secondStage.show();
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MainClass mc = new MainClass();
				try {
					mc.start(secondStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		createButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				String name = fullNameField.getText();
				String pass1 = passField1.getText();
				String pass2 = passField2.getText();
				String mail = mailField.getText();
				String ph = phField.getText();
				String add = addField.getText();
				
				if ((name.isEmpty()) || (pass1.isEmpty()) || (mail.isEmpty()) || (ph.isEmpty()) || (add.isEmpty())){
					
					Alert error1 = new Alert(AlertType.ERROR);
					error1.setContentText("Some fields are Empty..!!");
					error1.show();
					
				}else {
					UserDataEntry fw = new UserDataEntry();
					
					if (fw.ifTrue(mail) == true){
						Alert error2 = new Alert(AlertType.ERROR);
						error2.setContentText("A user with this email already exists, please try another email...!!");
						error2.show();
					}
					else {
						if (pass1.equals(pass2)){
							// data is to be pushed from here...!!
							fw.enterRecord(name, pass1, mail, ph, add);
							
							fullNameField.setText("");
							passField1.setText("");
							passField2.setText("");
							mailField.setText("");
							phField.setText("");
							addField.setText("");
							usernameField.setText("");
							
							Alert success = new Alert(AlertType.CONFIRMATION);
							success.setContentText("Your have been successfully registered...!");
							success.show();
						}
						else{					
							Alert error3 = new Alert(AlertType.ERROR);
							error3.setContentText("Your Passwords do not match...!!");
							error3.show();
						}
					}
				}
			}
		});
		
	}

}
