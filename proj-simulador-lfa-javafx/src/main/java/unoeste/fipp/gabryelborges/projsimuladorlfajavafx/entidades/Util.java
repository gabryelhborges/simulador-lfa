package unoeste.fipp.gabryelborges.projsimuladorlfajavafx.entidades;

import javafx.scene.control.Alert;

public class Util {
    public static void exibirMensagem(String titulo, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
