package unoeste.fipp.gabryelborges.projsimuladorlfajavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Button bttExpReg;
    public Button bttAutomato;
    public Button bttGramReg;

    //Funcao chamada quando o bttExpReg é clicado
    public void irTelaExpReg(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exp-reg-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Expressões Regulares");
        stage.show();
    }

    public void irTelaAutomatos(ActionEvent actionEvent) {
        System.out.println("Automatos");
    }

    public void irTelaGramReg(ActionEvent actionEvent) {
        System.out.println("GramReg");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println("HelloController");
    }
}