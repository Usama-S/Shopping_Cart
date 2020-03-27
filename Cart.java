
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Cart extends Application {
	File toRead;
	Cart (Stage lastStage){
	//public static void main(String[] args){
		//launch(args);
		try {
			start(lastStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//header start
				ImageView logo = new ImageView("file:///D:/Shopping_Cart/logo1.png");
				logo.setFitHeight(80);
				logo.setFitWidth(300);
				
				Text yourCart = new Text("Bill");
				yourCart.setStyle("-fx-font-size : 42");
				yourCart.setFill(Color.WHITE);
				
				HBox cart = new HBox();
				cart.getChildren().add(yourCart);
				cart.setAlignment(Pos.BASELINE_RIGHT);
				cart.setPadding(new Insets(10, 0, 0, 0));
				cart.setPrefWidth(350);
				
				Button logout = new Button("Logout");
				logout.setPrefWidth(80);
				
				HBox buttons = new HBox(5);
				buttons.getChildren().addAll(logout);
				buttons.setPadding(new Insets(20, 10, 0, 0));
				buttons.setAlignment(Pos.BASELINE_RIGHT);
				buttons.setPrefWidth(350);;
				
				HBox header = new HBox();
				header.getChildren().addAll(logo, cart, buttons);
				header.setPrefWidth(1000);
				header.setPadding(new Insets(10, 20, 10, 20));
				header.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
				
				VBox head = new VBox();
				head.getChildren().addAll(header);
				//header end
				
				//Cart start
				
				GridPane gp2 = new GridPane();
				gp2.setVgap(20);
				gp2.setHgap(50);
				gp2.setAlignment(Pos.CENTER);

				CartItems ci = new CartItems();
				ci.orderExtraction(gp2);
				
				Label nameLabel = new Label("Name :");
				Text name = new Text(ci.customerName);
				name.setStyle("-fx-font-weight: bold");

				gp2.add(nameLabel, 0, 0);
				gp2.add(name, 1, 0);

				Label phLabel = new Label("Ph # :");
				Text ph = new Text(ci.customerPh);
				ph.setStyle("-fx-font-weight: bold");

				gp2.add(phLabel, 0, 1);
				gp2.add(ph, 1, 1);
				
				Label addLabel = new Label("Delivery Address :");
				Text add = new Text(ci.customerAdd);
				add.setStyle("-fx-font-weight: bold");

				gp2.add(addLabel, 0, 2);
				gp2.add(add, 1, 2);
				
				Text heading = new Text("Your Order");
				heading.setStyle("-fx-font-size: 18pt");
				
				Text t1 = new Text("Title");
				t1.setStyle("-fx-font-weight: bold");
				Text t2 = new Text("Unit Price");
				t2.setStyle("-fx-font-weight: bold");
				Text t3 = new Text("Qty");
				t3.setStyle("-fx-font-weight: bold");
				Text t4 = new Text("Total Price");
				t4.setStyle("-fx-font-weight: bold");
				
				Label bil = new Label("Total amount");
				bil.setStyle("-fx-font-weight: bold");
				Text bill = new Text(Integer.toString(ci.sum));
				bill.setStyle("-fx-font-weight: bold");
				
				Button confirm = new Button("Place Order");

				gp2.add(heading, 1, 3, 2, 1);
				gp2.add(t1, 0, 4);
				gp2.add(t2, 1, 4);
				gp2.add(t3, 2, 4);
				gp2.add(t4, 3, 4);
				
				gp2.add(bil, 0, ci.lastGpIndex+2);
				gp2.add(bill, 2, ci.lastGpIndex+2);
				
				gp2.add(confirm, 1, ci.lastGpIndex+3, 2, 1);
				
				
				ScrollPane sp = new ScrollPane();
				VBox scroll = new VBox();
				scroll.getChildren().addAll(sp, gp2);
				sp.setContent(gp2);
				sp.setPrefSize(1000, 650);
				sp.setPadding(new Insets(20, 0, 0, 250));
				
				
				//Cart end
				
				
				
				VBox vbox = new VBox();
				vbox.getChildren().addAll(head, sp);

				Scene allProducts = new Scene(vbox, 1000, 650);
				primaryStage.setScene(allProducts);
				primaryStage.show();
					
				
				logout.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						primaryStage.hide();
						MainClass mc = new MainClass();
						try {
							mc.start(primaryStage);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
				confirm.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Alert a = new Alert(AlertType.CONFIRMATION);
						a.setContentText("Your Order has been placed successfully");
						a.showAndWait();
					}
				});
				
	}
}
