package unoeste.fipp.gabryelborges.projsimuladorlfajavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import unoeste.fipp.gabryelborges.projsimuladorlfajavafx.entidades.Gramatica;
import unoeste.fipp.gabryelborges.projsimuladorlfajavafx.entidades.Util;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GramRegViewController implements Initializable {
    public TextField txtFieldV;
    public TextField txtFieldT;
    public TextField txtFieldP;
    public TextField txtFieldS;
    public TextField txtFieldD;
    public Button bttVerificaGramatica;
    public Button bttPassoPasso;
    private Gramatica gramatica;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valorPadraoTxtFields();
        desabilitaBotoes();
        addTextFieldListeners();
    }

    private void addTextFieldListeners() {//verifica evento dos fields e desabilita os botoes
        txtFieldV.textProperty().addListener((observable, oldValue, newValue) -> desabilitaBotoes());
        txtFieldT.textProperty().addListener((observable, oldValue, newValue) -> desabilitaBotoes());
        txtFieldP.textProperty().addListener((observable, oldValue, newValue) -> desabilitaBotoes());
        txtFieldS.textProperty().addListener((observable, oldValue, newValue) -> desabilitaBotoes());
        txtFieldD.textProperty().addListener((observable, oldValue, newValue) -> desabilitaBotoes());
    }

    public void desabilitaBotoes() {
        bttPassoPasso.setDisable(true);
        bttVerificaGramatica.setDisable(true);
    }

    @FXML
    private void definirGramatica() {
        if(todosFieldsPreenchidos()) {
            removeEspacosTxtFields();
            List<String> V = List.of(txtFieldV.getText().split(","));
            List<String> T = List.of(txtFieldT.getText().split(","));
            List<String> P = List.of(txtFieldP.getText().split(","));
            String S = txtFieldS.getText();

            gramatica = new Gramatica(V, T, P, S);
            bttVerificaGramatica.setDisable(false);
            bttPassoPasso.setDisable(false);
        }else{
            Util.exibirMensagem("Preencha todos os campos", null, "Preencha todos os campos", null);
        }
    }

    private boolean todosFieldsPreenchidos() {
        return !txtFieldV.getText().isEmpty() && !txtFieldT.getText().isEmpty() && !txtFieldP.getText().isEmpty() && !txtFieldS.getText().isEmpty() && !txtFieldD.getText().isEmpty();
    }

    private void removeEspacosTxtFields() {
        txtFieldV.setText(txtFieldV.getText().replaceAll(" ", ""));
        txtFieldT.setText(txtFieldT.getText().replaceAll(" ", ""));
        txtFieldP.setText(txtFieldP.getText().replaceAll(" ", ""));
        txtFieldS.setText(txtFieldS.getText().replaceAll(" ", ""));
    }

    @FXML
    private void valorPadraoTxtFields() {
        txtFieldV.setText("A,B,C");//variaveis
        txtFieldT.setText("a,b,c");//simbolos terminais
        txtFieldP.setText("A->aB,A->aC,B->bC,B->b,B->bA,C->cA,C->c");//regras de producao
        txtFieldS.setText("A");//simbolo inicial
        txtFieldD.setText("abc");//cadeia
        //txtFieldP.setText("A->aB,A->aC,B->bC,C->cA");
    }

    @FXML
    private void executarPassoAPasso() {
        // Implementar lógica de execução passo a passo
    }

    public void verificaGramatica(ActionEvent actionEvent) {
        if (gramatica != null) {
            if (gramatica.getV().contains(gramatica.getS())) {
                String D = txtFieldD.getText();
                String atual = gramatica.getS();
                for (int i = 0; i < D.length(); i++) {
                    String simbolo = String.valueOf(D.charAt(i));
                    if (gramatica.getT().contains(simbolo)) {
                        boolean encontrou = false;
                        for (String producao : gramatica.getP()) {
                            if (!encontrou) {
                                if (producao.startsWith(atual + "->") && producao.contains(simbolo)) {
                                    String derivacao = producao.substring(producao.indexOf(">") + 1);
                                    String proxSimbolo = (i + 1 < D.length()) ? String.valueOf(D.charAt(i + 1)) : "";
                                    String proxVar = derivacao.replaceAll("[^A-Z]", "");

                                    // Check if the derivation can generate a terminal symbol directly
                                    if (proxSimbolo.isEmpty() && derivacao.equals(simbolo)) {
                                        atual = "";
                                        encontrou = true;
                                    } else if (gramatica.podeGerar(proxVar, proxSimbolo)) {
                                        atual = proxVar;
                                        encontrou = true;
                                    }
                                }
                            }
                        }
                        if (!encontrou) {
                            Util.exibirMensagem("Gramática inválida", null, "Gramática inválida", Alert.AlertType.ERROR);
                            return;
                        }
                    } else {
                        Util.exibirMensagem("Gramática inválida", null, "Gramática inválida", Alert.AlertType.ERROR);
                        return;
                    }
                }
                // Verificação adicional para garantir que todas as variáveis foram consumidas
                if (atual.chars().anyMatch(Character::isUpperCase)) {
                    Util.exibirMensagem("Gramática inválida: Variáveis não totalmente consumidas", null, "Gramática inválida", Alert.AlertType.ERROR);
                } else {
                    Util.exibirMensagem("Gramática válida", null, "Gramática válida", Alert.AlertType.INFORMATION);
                }
            } else {
                Util.exibirMensagem("Gramática inválida", null, "Gramática inválida", Alert.AlertType.ERROR);
            }
        } else {
            Util.exibirMensagem("Defina a gramática", null, "Defina a gramática", Alert.AlertType.ERROR);
        }
    }



    /*

    public void verificaGramatica(ActionEvent actionEvent) {
        String aux;
        if(gramatica != null) {
            if(gramatica.getV().contains(gramatica.getS())) {
                String D = txtFieldD.getText();
                String atual = gramatica.getS();
                for (int i = 0; i < D.length(); i++) {
                    String simbolo = String.valueOf(D.charAt(i));
                    if(gramatica.getT().contains(simbolo)) {
                        boolean encontrou = false;
                        for (String producao : gramatica.getP()) {
                            if(!encontrou) {
                                if (producao.startsWith(atual + "->") && producao.contains(simbolo)) {

                                    //pegar somente letras maiusculas(variaveis)
                                    atual = producao.substring(producao.indexOf(">") + 1).replaceAll("[^A-Z]", "");
                                    encontrou = true;
                                }
                            }
                        }
                        if(!encontrou) {
                            Util.exibirMensagem("Gramática inválida", null, "Gramática inválida", Alert.AlertType.ERROR);
                            return;
                        }
                    }else{
                        Util.exibirMensagem("Gramática inválida", null, "Gramática inválida", Alert.AlertType.ERROR);
                        return;
                    }
                }
                Util.exibirMensagem("Gramática válida", null, "Gramática válida", Alert.AlertType.INFORMATION);
            }else{
                Util.exibirMensagem("Gramática inválida", null, "Gramática inválida", Alert.AlertType.ERROR);
            }
        }else{
            Util.exibirMensagem("Defina a gramática", null, "Defina a gramática", Alert.AlertType.ERROR);
        }
    }
     */
}



















































