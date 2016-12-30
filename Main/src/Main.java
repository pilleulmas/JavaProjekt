import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Created by Pille on 18.10.2016.
 */
public class Main extends Application {
    static int max =10;
    static Tehted[] tehted = new Tehted[5];

    @Override
    public void start(Stage primaryStage) throws Exception {

       Label pealkiri = new Label("Vali maksimaalne ja tehte liik!");

        HBox valik = new HBox();
        valik.setAlignment(Pos.BOTTOM_CENTER);
        valik.setSpacing(20);

        Label slText = new Label();
        slText.setFont(Font.font ("Verdana", 20));
        slText.setText("Vali maksimaalne\nkordaja/liidetav");
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
            max = slider.valueProperty().intValue();
            System.out.println(max);
        });

        VBox vasak = new VBox();
        vasak.setAlignment(Pos.TOP_CENTER);
        vasak.getChildren().addAll(slText, slider, slmax);

        GridPane kesk = new GridPane();
        kesk.setAlignment(Pos.TOP_CENTER);
        kesk.add(pealkiri, 1, 0,4,1);

        Label[] ylesanded = new Label[5];
        TextField[] vastused = new TextField[5];
        Label[] oiged = new Label[5];

        for (int i=0; i<5; i++) {
            ylesanded[i]=new Label("");
            vastused[i]=new TextField("Sisesta vastus");
            oiged[i]=new Label("");

            kesk.add(ylesanded[i],0,i+1);
            kesk.add(vastused[i],1,i+1);
            kesk.add(oiged[i],2,i+1);
        }

        Button kontroll = new Button("Kontroll");
        kontroll.setOnAction((event) -> {
            System.out.println("Kontrollin vastused!");
            for(int i =0; i<5;i++){
                try{
                    if(Integer.parseUnsignedInt(vastused[i].getText())== tehted[i].c) {
                        oiged[i].setText("Õige vastus!");
                    } else {oiged[i].setText("Vale vastus, õige on "+tehted[i].c);}
                }
                catch(Exception e) {
                    oiged[i].setText("Vastus on puudu!");
                }
            }
        });

        kesk.add(kontroll, 1, 6);

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
        ToggleButton e5 = new ToggleButton("Järjestamine");
        e5.setUserData("J2rjestamine");
        e1.setToggleGroup(g);
        e2.setToggleGroup(g);
        e3.setToggleGroup(g);
        e4.setToggleGroup(g);
        e5.setToggleGroup(g);
        g.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(g.getSelectedToggle().getUserData().toString());

            for (int i = 0; i<5; i++) {
                tehted[i] = new Tehted();
                tehted[i].genereeri(max, g.getSelectedToggle().getUserData().toString());
                ylesanded[i].setText(tehted[i].a+" "+tehted[i].mark+" "+tehted[i].b);
                vastused[i].setText("");
            }
        });

        valik.getChildren().addAll(e1, e2, e3, e4, e5);
        primaryStage.setScene(esimene);

    }
}