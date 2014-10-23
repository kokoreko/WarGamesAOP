package TzukEitan.remote.main;

import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class RemoteWarClinet extends Application {
		public static void main(String[] arguments) {
			launch(arguments);
		}
		@Override
		public void start(final Stage stage) throws Exception {
			FXMLLoader f = new FXMLLoader();

			Parent fxmlRoot = (Parent) f.load(new FileInputStream(new File(
					"src\\TzukEitan\\remote\\main.fxml")));
			Font.loadFont(RemoteWarClinet.class.getResource("DS-DIGII.TTF").toExternalForm(),12);

			Scene scene = new Scene(fxmlRoot);
			
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
		
		}
}
