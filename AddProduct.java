
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class AddProduct extends Application {
	
	AddProduct(Stage secondStage){
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
		
		Label title = new Label("Title");
		TextField titleField = new TextField();
		titleField.setPromptText("Product Title");
		
		Label price = new Label("Price");
		TextField priceField = new TextField();
		priceField.setPromptText("e.g. 450");
		
		Label path = new Label("File Path");
		TextField pathField = new TextField();
		pathField.setPromptText("file:///D:/Folder/image.extension");
		
		Label category = new Label("Category");
		ComboBox catField = new ComboBox();
		catField.getItems().addAll("men","women","child");
		catField.setPromptText("Category");
		
		Label description = new Label("Description");
		TextArea desField = new TextArea();
		desField.setMaxSize(220, 100);
		
		Button addButton = new Button("Add");
		Button back = new Button("Back");
		
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(back, addButton);
		buttons.setAlignment(Pos.CENTER);
		
		
		GridPane gp2 = new GridPane();
						
		gp2.setAlignment(Pos.CENTER);
		
		gp2.add(img, 0, 0, 2, 1);
		
		gp2.add(title, 0, 1);
		gp2.add(titleField, 1, 1);

		gp2.add(price, 0, 2);
		gp2.add(priceField, 1, 2);

		gp2.add(path, 0, 3);
		gp2.add(pathField, 1, 3);

		gp2.add(category, 0, 4);
		gp2.add(catField, 1, 4);
		
		gp2.add(description, 0, 5);
		gp2.add(desField, 1, 5);
		
		gp2.add(buttons, 0, 6, 2, 1);
		
		GridPane.setHalignment(addButton, HPos.CENTER);
		GridPane.setHalignment(img, HPos.CENTER);
		
		gp2.setVgap(20);
		gp2.setHgap(20);
				
		Scene signup = new Scene(gp2, 1000, 650);
		secondStage.setScene(signup);
		secondStage.show();
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AdminPanel mc = new AdminPanel(secondStage);
			}
		});
		
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				String prodTitle = titleField.getText();
				String prodPrice = priceField.getText();
				String prodCategory = null;
				prodCategory = (String) catField.getSelectionModel().getSelectedItem();
				String imgPath = pathField.getText();
				String prodDescription = desField.getText();
				
				if ((prodTitle.isEmpty()) || (imgPath.isEmpty()) || (prodDescription.isEmpty()) || (prodPrice.isEmpty())){
					
					Alert error1 = new Alert(AlertType.ERROR);
					error1.setContentText("Some fields are Empty..!!");
					error1.show();
					
				}
				else if (prodCategory == null){
					Alert nullProductCategory = new Alert(AlertType.ERROR);
					nullProductCategory.setContentText("Please Choose a Catgeroy!");
					nullProductCategory.show();
				}
				else {
					ProductAddRemove fw = new ProductAddRemove();
					
					try{
						int productPrice = Integer.parseInt(prodPrice);
						fw.addProduct(prodTitle, productPrice, imgPath, prodCategory, prodDescription);
						
						Alert success = new Alert(AlertType.CONFIRMATION);
						success.setContentText("Product added successfully!");
						success.showAndWait();
						
						titleField.setText(null);
						priceField.setText(null);
						pathField.setText(null);
						desField.setText(null);
						catField.getSelectionModel().clearSelection();
					}
					catch (Exception e) {
						// TODO: handle exception
						Alert forPrice = new Alert(AlertType.ERROR);
						forPrice.setContentText("Invalid Price! Enter Price in Integer format");
						forPrice.show();
					}
				}	
			}			
		});	
	}
}
