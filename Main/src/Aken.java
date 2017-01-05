import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * Created by Pille on 2.01.2017.
 */
public class Aken {
    private static int max =10;
    private static Tehted[] tehted = new Tehted[10];
    private Stage stage = new Stage();

    public Aken(){
        algaken();
    }
    private void algaken(){

        HBox valik = new HBox();                                //Ülemise paneeli välimus
            valik.setAlignment(Pos.BOTTOM_CENTER);
            valik.setSpacing(20);

        Label slText = new Label();                             //slideri pealkiri
            slText.setFont(Font.font ("Verdana", 20));
            slText.setText("Vali maksimaalne\nkordaja/liidetav");
            slText.setAlignment(Pos.BOTTOM_CENTER);
        Label slmax = new Label();
        Slider slider = new Slider();                           //tehete maksimaalse valikuks slider
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
        });

        VBox vasak = new VBox();                                //vasakpoolne akna sisu
            vasak.setAlignment(Pos.TOP_CENTER);
            vasak.getChildren().addAll(slText, slider, slmax);  //vasakpoolse sisu seadistus

        Label pealkiri = new Label("Vali maksimaalne ja tehte liik!");
        pealkiri.setFont(Font.font ("Verdana", 20));
        GridPane kesk = new GridPane();                         //keskpaneel
            kesk.setAlignment(Pos.TOP_CENTER);
            kesk.add(pealkiri, 1, 0,4,1);

        GridPane keskJ2r = new GridPane();                         //keskpaneel
        keskJ2r.setAlignment(Pos.TOP_CENTER);

        Label[] ylesanded = new Label[10];                      //massiivid keskpaneeli sisu jaoks
        TextField[] vastused = new TextField[10];
        Label[] oiged = new Label[10];

            for (int i=0; i<10; i++) {                          //keskpaneeli tühi sisu
            ylesanded[i]=new Label("");
                ylesanded[i].setFont(Font.font ("Verdana", 15));
            vastused[i]=new TextField("Sisesta vastus");
                vastused[i].setFont(Font.font ("Verdana", 15));
            oiged[i]=new Label("");
                oiged[i].setFont(Font.font ("Verdana", 15));

            kesk.add(ylesanded[i],0,i+1);                       //keskpaneeli seadistus
            kesk.add(vastused[i],1,i+1);
            kesk.add(oiged[i],2,i+1);
        }

        Button kontroll = new Button("Kontroll");               //keskpaneeli vastuste kontrollimise nupp
        kontroll.setFont(Font.font ("Verdana", 20));
            kontroll.setOnAction((event) -> {
            System.out.println("Kontrollin vastused!");
            for(int i =0; i<10;i++){                            //kontrollib ükshaaval vastused üle
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

        kesk.add(kontroll, 1, 11);                          //kontrolli nupp keskpaneelile

        BorderPane aken = new BorderPane();                 //terve aken BorderPane
            aken.setTop(valik);
            aken.setCenter(kesk);
            aken.setLeft(vasak);
        Scene esimene = new Scene(aken, 800, 600);          //Stseeni suurus
            stage.setScene(esimene);
            stage.show();

        ToggleGroup g = new ToggleGroup();                  //valiku nupud
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
            e1.setToggleGroup(g);                           //nupud ühtse grupi alla
            e2.setToggleGroup(g);
            e3.setToggleGroup(g);
            e4.setToggleGroup(g);
            e5.setToggleGroup(g);
            g.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {    //valiku tulemus
                if (g.getSelectedToggle().getUserData().toString() == "J2rjestamine") {

                    boolean tunnus;
                    int loendur = 0;
                    int arv;
                    int j2rjestatud[] = new int[12];
                    int numbrid[] = new int[12];
                    for (int mitmes = 0; mitmes < 12; mitmes++) {   //teen 12-se massiivi suvanumbritest
                        do {
                            tunnus = false;
                            if (max < 12) {
                                arv = (int) Math.floor(Math.random() * 12) + 1;
                            } else {
                                arv = (int) Math.floor(Math.random() * max) + 1;
                            }
                            for (int i = 0; i < mitmes; i++)
                                if (arv == numbrid[i]) tunnus = true;
                        } while (tunnus);
                        numbrid[mitmes] = arv;          //lisan arvu segamini massiivi
                        j2rjestatud[mitmes] = arv;      //lisan arvu j2rjestamise massiivi
                    }

                    Arrays.sort(j2rjestatud);           //sorteerin j2rjestamise massiivi
                    System.out.println(Arrays.toString(numbrid));
                    System.out.println(Arrays.toString(j2rjestatud));

                    for (int j = 0; j < 12; j++) {      //asetan numbritega nupud gridPanele
                        Button nupp = new Button(Integer.toString(numbrid[j]));
                        keskJ2r.add(nupp, j % 3, j / 3);
                        nupp.setOnAction((event) -> {

                            //j2rKontroll();
                            System.out.println(nupp.getText());
                            if (Integer.parseInt(nupp.getText())==j2rjestatud[loendur]){
                                System.out.println("Tubli!");
                            }
                        });
                    }
                    /*public void j2rKontroll(){
                        if (Integer.parseInt(nupp.getText())==j2rjestatud[loendur]){
                            System.out.println("Tubli!");
                            loendur++;
                        }
                    }*/
                    aken.setCenter(keskJ2r);                           //keskosa täitmine järjestamise nuppudega

                }
                else {
                    aken.setCenter(kesk);
                    for (int i = 0; i < 10; i++) {                    //tühja keskosa täitmine valitud ülesannetega
                        tehted[i] = new Tehted();
                        tehted[i].genereeri(max, g.getSelectedToggle().getUserData().toString());   //Tehete genereerimise meetodi kasutus
                        ylesanded[i].setText(tehted[i].a + " " + tehted[i].mark + " " + tehted[i].b);
                        vastused[i].setText("");
                        oiged[i].setText("");
                    }
                }
            });
        valik.getChildren().addAll(e1, e2, e3, e4, e5);     //Valiku nupud ülapaneelile
    }
}
