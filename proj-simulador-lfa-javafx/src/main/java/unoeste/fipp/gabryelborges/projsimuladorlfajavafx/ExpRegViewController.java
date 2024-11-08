package unoeste.fipp.gabryelborges.projsimuladorlfajavafx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import unoeste.fipp.gabryelborges.projsimuladorlfajavafx.entidades.Util;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static unoeste.fipp.gabryelborges.projsimuladorlfajavafx.entidades.Util.exibirMensagem;

public class ExpRegViewController implements Initializable {
    private static final String ER_PATTERN = "^[0-9a-zA-Z*+|().ε{},]+$";
    public TextField expRegField;
    public TextField entradaField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicialização do controlador com a ER básica

    }

    public boolean ehExpRegValida(String er) { // Verifica se a ER é válida
        return er.matches(ER_PATTERN);
    }

    public boolean testaExpReg(String er, String input) { // Compila e testa a ER
        Pattern pattern = Pattern.compile(er);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public void bttTestarEntradaClick(ActionEvent actionEvent) {
        String er = expRegField.getText();
        er = er.replace("ε", "").replace(".", "");
        String input = entradaField.getText();
        if (testaExpReg(er, input)) {
            Util.exibirMensagem("Entrada aceita", null, "Entrada aceita", Alert.AlertType.INFORMATION);
        } else {
            Util.exibirMensagem("Entrada não aceita", null, "Entrada não aceita", Alert.AlertType.ERROR);
        }
    }

    public void eventoInputExpReg(KeyEvent keyEvent) {
        String er = expRegField.getText();
        if (er.length() > 0) {
            if (!ehExpRegValida(er)) {
                exibirMensagem("Expressão inválida", null, "Expressão inválida", Alert.AlertType.ERROR);
                String invalidChar = keyEvent.getCharacter();
                expRegField.setText(er.replace(invalidChar, "")); // Remove o caractere inválido
            }
        }
    }



    public void bttPipelineClick(ActionEvent actionEvent) {
        expRegField.setText(expRegField.getText() + "|");
        expRegField.requestFocus();
        expRegField.positionCaret(expRegField.getText().length());

    }

    public void bttVazioClick(ActionEvent actionEvent) {
        expRegField.setText(expRegField.getText() + "ε");
        expRegField.requestFocus();
    }
}
