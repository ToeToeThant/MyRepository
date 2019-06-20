package application;
	
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

@SpringBootApplication
public class Main extends Application {
	private static ConfigurableApplicationContext ctx;
	
	public static<T> T getController(Class<T> type) {
		return ctx.getBean(type);
	}
	
	private void inti() {
		try {
			super.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx=SpringApplication.run(Main.class);

	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		ctx.close();
	}
	
	@Override
	public void start(Stage stage) {
		FXMLLoader loader=new FXMLLoader(SampleController.class.getResource("Sample.fxml"));
		loader.setControllerFactory(ctx::getBean);
		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
