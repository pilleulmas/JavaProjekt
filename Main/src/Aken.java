import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Arrays;
import static javafx.scene.paint.Color.*;

/**
 * Created by Pille on 2.01.2017.
 */
public class Aken {
    private static int max = 10;
    private static Tehted[] tehted = new Tehted[10];
    private Stage stage = new Stage();
    String nupuvalik[] = {"Liitmine", "Lahutamine", "Korrutamine", "Jagamine", "Järjestamine"};

    public static long start, aeg;
    public static int aegaKulus = 0;
    public static GridPane kesk = new GridPane();               //Keskpaneel
    int ylArv = 10;

    Label pealkiri = new Label("Vali maksimaalne ja tehte liik!");
    Label[] ylesanded = new Label[ylArv];                      //massiivid arvutamise keskpaneeli sisu jaoks
    TextField[] vastused = new TextField[ylArv];
    Label[] oiged = new Label[ylArv];
    Button kontroll = new Button("Kontroll");                   //Arvutamise kontrolli nupp

    BorderPane aken = new BorderPane();                         //Terve aken BorderPane stiilis

    public Aken(){
        algaken();
    }
    public void algaken(){

        HBox valik = new HBox();                                //Ülemise paneeli välimus
            valik.setAlignment(Pos.BOTTOM_CENTER);
            valik.setSpacing(20);

        Label slText = new Label("Vali maksimaalne\ntegur");    //slideri pealkiri
            slText.setFont(Font.font ("Verdana", 20));
        Label slmax = new Label("Max on 10");
            slmax.setFont(Font.font ("Verdana", 20));
        Slider slider = new Slider(0,100,10);                   //slider maksimumi valikuks
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(10);
            slider.setOrientation(Orientation.VERTICAL);
        slider.setMinSize(Control.USE_PREF_SIZE,250);
            slider.valueProperty().addListener((observable, vanaVaartus, uusVaartus) -> {
            slmax.setText("Max on " + uusVaartus.intValue());
            max = slider.valueProperty().intValue();
        });
        Label ylText = new Label("Vali ülesannete arv");        //Ülesannete koguse slideri pealkiri
        ylText.setFont(Font.font ("Verdana", 20));
        Label slyl = new Label("Ülesandeid tuleb 10");
        slyl.setFont(Font.font ("Verdana", 20));
        Slider ylSlider = new Slider(1,10,10);                   //Ülesannete koguse valikuks slider
        ylSlider.setShowTickLabels(true);
        ylSlider.setShowTickMarks(true);
        ylSlider.setMajorTickUnit(1);
        ylSlider.setOrientation(Orientation.VERTICAL);
        ylSlider.setMinSize(Control.USE_PREF_SIZE,100);
        ylSlider.valueProperty().addListener((observable, vanaVaartus, uusVaartus) -> {
            slyl.setText("Ülesandeid tuleb " + uusVaartus.intValue());
            ylArv = ylSlider.valueProperty().intValue();
        });

        VBox vasak = new VBox();                                //vasakpoolne akna sisu
            vasak.setAlignment(Pos.TOP_CENTER);
            vasak.getChildren().addAll(slText, slider, slmax, ylText, ylSlider, slyl);  //vasakpoolse sisu seadistus

        pealkiri.setFont(Font.font ("Verdana", 20));
            kesk.setAlignment(Pos.TOP_CENTER);              //keskpaneeli algseis
            kesk.add(pealkiri, 1, 0,4,1);

        kontroll.setFont(Font.font ("Verdana", 20));
        kontroll.setOnAction((event) -> {                   //kontrolli nupuvajutuse tegevus
            aeg = System.currentTimeMillis() - start;
            aegaKulus = (int)aeg/1000;
            System.out.println("Kontrollin vastused!");
            System.out.println(aeg/1000);
            for(int i =0; i<ylArv;i++){                            //kontrollib ükshaaval vastused üle
                try{
                    if(Integer.parseUnsignedInt(vastused[i].getText())== tehted[i].c) {
                        oiged[i].setText("Õige vastus!");
                        oiged[i].setTextFill(BLACK);
                    } else {oiged[i].setText("Vale vastus, õige on "+tehted[i].c);
                        oiged[i].setTextFill(RED);
                    }
                }
                catch(Exception e) {
                    oiged[i].setText("Vastus on puudu!");
                    oiged[i].setTextFill(ORANGE);
                }
            }
            Label aeg = new Label("");
            aeg.setText("Aega kulus "+aegaKulus+" sekundit");
            aeg.setFont(Font.font ("Verdana", 20));
            if( kesk.getChildren().get(kesk.getChildren().size()-1).getClass().getName().equals( "javafx.scene.control.Label")) {

                Label viimane = (Label) kesk.getChildren().get(kesk.getChildren().size()-1);
                viimane.setText("");
            }
            kesk.add(aeg,0,12,4,1);
        });

        System.out.println(aegaKulus);

            aken.setTop(valik);                             //Kogu akna täitmine
            aken.setCenter(kesk);
            aken.setLeft(vasak);
        Scene esimene = new Scene(aken, 800, 600);          //Stseeni suurus
            stage.setScene(esimene);
            stage.show();

        for(int o=0; o<5; o++) {
            String liik = nupuvalik[o];
            Button valikunupp = new Button(nupuvalik[o]);
            valik.getChildren().add(valikunupp);
            valikunupp.setStyle("-fx-font: 15 verdana; -fx-base: #b6e7c9;");
            valikunupp.setMinSize(120.0, Control.USE_PREF_SIZE);
            valikunupp.setOnAction(e -> {
                start = System.currentTimeMillis();
                aegaKulus=0;
                System.out.println(liik);
                System.out.println(start);
                kesk = teeKesk(liik);
                aken.setCenter(kesk);
            });
        }
    }
    public Button looNupp(String text) {               //järjestamise nuppude loomine
        Button nupp = new Button(text);
        nupp.setStyle("-fx-font: 20 verdana; -fx-base: #b6e7c9;");
        nupp.setMinSize(150.0, Control.USE_PREF_SIZE);
        nupp.setOnAction(e -> {J2rjestamine.j2rKontroll(nupp.getText()); System.out.println(text);});
        return nupp ;
    }

    public GridPane teeKesk(String liik) {
        GridPane tagasi = new GridPane();
        if (liik == "Järjestamine") {
            //j2rjestamise keskpaneel
            tagasi.setAlignment(Pos.TOP_CENTER);
            Label juhend = new Label("Kliki nuppudel suurenevas järjekorras!");
            juhend.setFont(Font.font ("Verdana", 20));
            tagasi.add(juhend,0,0,4,1);
            J2rjestamine.vead.setFont(Font.font ("Verdana", 20));
            J2rjestamine.viimane.setFont(Font.font ("Verdana", 20));
            tagasi.add(J2rjestamine.viimane,0,5,4,1);
            tagasi.add(J2rjestamine.vead,0,6,4,1);
            J2rjestamine.looMassiivid(max);

            for (int j = 0; j < 12; j++) {                  //asetan j2rjestamise nupud keskpaneelile
                Button nupp = looNupp(""+J2rjestamine.numbrid[j]);
                tagasi.add(nupp, (j % 3)+1, (j / 3)+1);
            }
            J2rjestamine.loendur = 0;
            J2rjestamine.aps = 0;
            J2rjestamine.vead.setText("");
            J2rjestamine.viimane.setText("");
            aken.setCenter(tagasi);                           //keskosa täitmine järjestamise nuppudega
        }
        else {
            tagasi = new GridPane();
            aken.setCenter(tagasi);
            pealkiri.setText(liik);
            tagasi.setAlignment(Pos.TOP_CENTER);
            tagasi.add(pealkiri, 1, 0,4,1);
            for (int i = 0; i < ylArv; i++) {                    //keskosa täitmine valitud ülesannetega
                tehted[i] = new Tehted();
                tehted[i].genereeri(max, liik);                 //Tehete genereerimise meetodi kasutus
                ylesanded[i]=new Label("");
                ylesanded[i].setFont(Font.font ("Verdana", 15));
                ylesanded[i].setText(tehted[i].a + " " + tehted[i].mark + " " + tehted[i].b);
                vastused[i]=new TextField("Sisesta vastus");
                vastused[i].setFont(Font.font ("Verdana", 15));
                vastused[i].setText("");
                oiged[i]=new Label("");
                oiged[i].setFont(Font.font ("Verdana", 15));
                oiged[i].setText("");
                tagasi.add(ylesanded[i],0,i+1);                       //keskpaneeli seadistus
                tagasi.add(vastused[i],1,i+1);
                tagasi.add(oiged[i],2,i+1);

            }
            tagasi.add(kontroll, 1, 11); //kontrolli nupp keskpaneelile
        }
        return tagasi;
    }
}
