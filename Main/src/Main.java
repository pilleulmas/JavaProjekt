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
        e1.setUserData("Liitmine");
        ToggleButton e2 = new ToggleButton("Lahutamine");
        e2.setUserData("Lahutamine");
        ToggleButton e3 = new ToggleButton("Korrutamine");
        e3.setUserData("Korrutamine");
        ToggleButton e4 = new ToggleButton("Jagamine");
        e4.setUserData("Jagamine");
        ToggleButton e5 = new ToggleButton("Võrdlemine");
        e5.setUserData("V6rdlemine");
        ToggleButton e6 = new ToggleButton("Järjestamine");
        e6.setUserData("J2rjestamine");
        e1.setToggleGroup(g);
        e2.setToggleGroup(g);
        e3.setToggleGroup(g);
        e4.setToggleGroup(g);
        e5.setToggleGroup(g);
        e6.setToggleGroup(g);
        g.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(g.getSelectedToggle().getUserData().toString());
            switch (g.getSelectedToggle().getUserData().toString()) {
                case "Liitmine":
                    System.out.println("Liitmine, hurraa!");;
                    break;
                case "Lahutamine":
                    System.out.println("Lahutamine, hurraa!");;
                    break;
                case "Korrutamine":
                    System.out.println("Korrutamine, hurraa!");;
                    break;
                case "Jagamine":
                    System.out.println("Jagamine, hurraa!");;
                    break;
                case "V6rdlemine":
                    System.out.println("Võrdlemine, hurraa!");;
                    break;
                case "J2rjestamine":
                    System.out.println("Järjestamine, hurraa!");;
                    break;
            }
        });

        vbox.getChildren().addAll(e1, e2, e3, e4, e5, e6);


        primaryStage.setScene(esimene);


    }
}
