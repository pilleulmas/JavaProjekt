import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
        Label pealkiri = new Label("Vali maksimaalne ja tehte liik!");

        HBox valik = new HBox();
        valik.setAlignment(Pos.BOTTOM_CENTER);
        valik.setSpacing(20);

        int max =10;
        Label slText = new Label();
        slText.setText("Vali maksimaalne\nkordaja/summa");
        slText.setAlignment(Pos.BOTTOM_CENTER);
        Label slmax = new Label();
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(10);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(10);
        slider.setOrientation(Orientation.VERTICAL);
        slider.valueProperty().addListener((observable, vanaVaartus, uusVaartus) -> {

            slmax.setText("Max on " + uusVaartus.intValue());
            //max = slider.valueProperty().intValue();
        });


        Button valiV22rtus = new Button();
        valiV22rtus.setText("Kinnita valik!");
        valiV22rtus.setOnAction((event) -> {
            //max.setValue(slider).intValue();
                System.out.println("Vajutasid nupule!");

        });
        VBox vasak = new VBox();
        vasak.setAlignment(Pos.TOP_CENTER);
        vasak.getChildren().addAll(slText, slider, slmax, valiV22rtus);

        GridPane kesk = new GridPane();
        kesk.setAlignment(Pos.TOP_CENTER);
        kesk.add(pealkiri, 1, 0,4,1);
        for (int i=1; i<6; i++) {
            Label yl = new Label("Ülesanne " +Integer.toString(i));
            kesk.add(yl, 0, i);
            TextField vastus = new TextField("Vastus "+ Integer.toString(i));
            kesk.add(vastus,1,i);
            Label oige = new Label("Õige vastus " + Integer.toString(i));
            kesk.add (oige,2,i);
        }

        BorderPane aken = new BorderPane();
        aken.setTop(valik);
        aken.setCenter(kesk);
        aken.setLeft(vasak);
        Scene esimene = new Scene(aken, 800, 600);
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
                    pealkiri.setText("LIITMINE");
                    //liitmine();
                    break;
                case "Lahutamine":
                    pealkiri.setText("LAHUTAMINE");
                    break;
                case "Korrutamine":
                    pealkiri.setText("KORRUTAMINE");
                    break;
                case "Jagamine":
                    pealkiri.setText("JAGAMINE");
                    break;
                case "V6rdlemine":
                    pealkiri.setText("VÕRDLEMINE");
                    break;
                case "J2rjestamine":
                    pealkiri.setText("JÄRJESTAMINE");
                    //j2rjestamine();
                    break;
            }
        });

        valik.getChildren().addAll(e1, e2, e3, e4, e5, e6);



        primaryStage.setScene(esimene);


    }
}
