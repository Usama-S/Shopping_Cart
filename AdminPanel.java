
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminPanel extends Application {

	String prodCategory = "all";
	
	AdminPanel (Stage fourthStage){
		try {
			start(fourthStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//header start
		ImageView logo = new ImageView("file:///D:/Shopping_Cart/logo1.png");
		logo.setFitHeight(80);
		logo.setFitWidth(300);
		
		Button all = new Button("All");
		all.setPrefWidth(80);
		Button men = new Button("Men");
		men.setPrefWidth(80);
		Button women = new Button("Women");
		women.setPrefWidth(80);
		Button child = new Button("Children");
		child.setPrefWidth(80);
		Button addProd = new Button("Add Product");
		addProd.setPrefWidth(100);
		Button logout = new Button("Logout");
		logout.setPrefWidth(80);
		
		HBox buttons = new HBox (5);
		buttons.getChildren().addAll(all, men, women, child, addProd, logout);
		buttons.setPadding(new Insets(20, 10, 0, 0));
		buttons.setAlignment(Pos.BASELINE_RIGHT);
		buttons.setPrefWidth(700);;
		
		VBox header = new VBox();
		header.getChildren().addAll(logo, buttons);
		header.setPrefWidth(1000);
		header.setPadding(new Insets(10, 20, 10, 20));
		header.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		/*Box buttons = new HBox(5);
		buttons.getChildren().addAll(all, men, women, child, addProd, logout);
		buttons.setPadding(new Insets(20, 10, 0, 0));
		buttons.setAlignment(Pos.BASELINE_RIGHT);
		buttons.setPrefWidth(700);;
		
		HBox header = new HBox();
		header.getChildren().addAll(logo, buttons);
		header.setPrefWidth(1000);
		header.setPadding(new Insets(10, 20, 10, 20));
		header.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));*/
		
		VBox head = new VBox();
		head.getChildren().addAll(header);
		//header end
		
		
		//Products start
		
		GridPane gp2 = new GridPane();
		gp2.setVgap(20);
		gp2.setHgap(50);
		gp2.setAlignment(Pos.CENTER);
				
		AdminProductExtraction pe = new AdminProductExtraction();
		pe.findRecord(gp2, "all", primaryStage);
		
		//Products end
		
		ScrollPane sp = new ScrollPane();
		VBox scroll = new VBox();
		scroll.getChildren().addAll(sp, gp2);
		sp.setContent(gp2);
		sp.setPrefSize(1000, 650);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(head, sp);
		
		Scene allProducts = new Scene(vbox, 1000, 650);
		primaryStage.setScene(allProducts);
		primaryStage.show();
				
		all.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gp2.getChildren().clear();
				pe.findRecord(gp2, "all", primaryStage);
			}
		});
		
		men.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gp2.getChildren().clear();
				pe.findRecord(gp2, "men", primaryStage);
			}
		});
		
		women.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gp2.getChildren().clear();
				pe.findRecord(gp2, "women", primaryStage);
				
			}
		});
		
		child.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gp2.getChildren().clear();
				pe.findRecord(gp2, "child", primaryStage);
			}
		});
		
		addProd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddProduct ap = new AddProduct(primaryStage);
			}
		});
		
		logout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
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
	}

}
