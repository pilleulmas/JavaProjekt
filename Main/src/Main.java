import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.event.TreeModelEvent;

/**
 * Created by Pille on 18.10.2016.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

       // Stage stage = new Stage();
       // StackPane stack = new StackPane();


        VBox vbox = new VBox();
        vbox.setSpacing(20);
        Scene esimene = new Scene(vbox, 400, 300);
        primaryStage.show();

        ToggleGroup g = new ToggleGroup();
        ToggleButton e1 = new ToggleButton("Liitmine");
        ToggleButton e2 = new ToggleButton("Lahutamine");
        ToggleButton e3 = new ToggleButton("Korrutamine");
        ToggleButton e4 = new ToggleButton("Jagamine");
        ToggleButton e5 = new ToggleButton("Võrdlemine");
        ToggleButton e6 = new ToggleButton("Järjestamine");
        e1.setToggleGroup(g);
        e2.setToggleGroup(g);
        e3.setToggleGroup(g);
        e4.setToggleGroup(g);
        e5.setToggleGroup(g);
        e6.setToggleGroup(g);

        vbox.getChildren().addAll(e1, e2, e3, e4, e5, e6);


        primaryStage.setScene(esimene);


    }
}
