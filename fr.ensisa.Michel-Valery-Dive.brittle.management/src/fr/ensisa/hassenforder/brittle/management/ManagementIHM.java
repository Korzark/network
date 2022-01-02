package fr.ensisa.hassenforder.brittle.management;

import java.io.IOException;

import fr.ensisa.hassenforder.brittle.management.network.ISession;
import fr.ensisa.hassenforder.brittle.management.network.ManagementSession;
import fr.ensisa.hassenforder.brittle.management.view.ManagementController;
import fr.ensisa.hassenforder.brittle.network.Protocol;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagementIHM extends Application {

	private ISession session;

	private Scene initRootLayout() {
       try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ManagementIHM.class.getResource("view/BrittleManagement.fxml"));
            Parent rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            ManagementController controller = loader.getController();
            controller.setSession(getSession());
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}

    public ISession getSession() {
		if (session == null) {
			session = new ManagementSession("localhost", Protocol.BANK_TCP_PORT);
			session.open();
		}
		return session;
	}

	@Override
	public void start(Stage primaryStage) {
		Scene scene = initRootLayout();
        primaryStage.setScene(scene);
		primaryStage.setTitle("Management Application");
        primaryStage.show();
	}

    public static void main(String[] args) {
		launch(args);
	}

}
