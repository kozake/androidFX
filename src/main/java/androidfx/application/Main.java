package androidfx.application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D visualBounds = primaryScreen.getVisualBounds();
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));

        //プロキシを使用しているなら設定を忘れずに
        //System.setProperty("http.proxyhost", "xxx");
        //System.setProperty("http.proxyport", "xxxx");
        //System.setProperty("https.proxyhost", "xxx");
        //System.setProperty("https.prozyport", "xxxx");
        WebView browser = (WebView) root.lookup("#browser");
        WebEngine engine = browser.getEngine();
        engine.load("http://google.com");

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("No Money..(; ;)", 0.7110),
                        new PieChart.Data("I have a lot of Money!!(((o(^ ^)o)))", 0.2790),
                        new PieChart.Data("Etc", 0.01)
                );

        PieChart chart = (PieChart) root.lookup("#PieChart");
        chart.setData(pieChartData);
        chart.setTitle("No Money or a lot of Money");

        TextArea shipping = (TextArea) root.lookup("#shipping");
        TextArea billing = (TextArea) root.lookup("#billing");
        billing.textProperty().bindBidirectional(shipping.textProperty());

        primaryStage.setTitle("JavaFX Advent Calendar 2014");
        primaryStage.setScene(new Scene(root, visualBounds.getWidth(), visualBounds.getHeight()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
