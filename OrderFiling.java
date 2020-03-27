import java.io.FileWriter;
import java.io.IOException;

public class OrderFiling {
	

	FileWriter orderFile;
	
	void fileCreter() throws IOException{
		orderFile = new FileWriter("orderFile.xml");
	}
	
}
