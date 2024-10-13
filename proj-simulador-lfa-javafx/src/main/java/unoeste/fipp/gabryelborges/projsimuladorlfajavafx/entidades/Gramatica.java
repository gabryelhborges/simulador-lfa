package unoeste.fipp.gabryelborges.projsimuladorlfajavafx.entidades;

import java.util.List;

public class Gramatica {
    private List<String> V;//Conjunto finito de simbolos variaveis ou nao-terminais
    private List<String> T;//Conjunto finito de simbolos terminais, disjunto de V
    private List<String> P;//Conjunto finito de regras de producao
    private String S;//Simbolo inicial, distinguido de V

    public Gramatica(List<String> V, List<String> T, List<String> P, String S) {
        this.V = V;
        this.T = T;
        this.P = P;
        this.S = S;
    }

    public boolean podeGerar(String var, String simbolo) {
        if (T.contains(simbolo)) {
            for (String producao : P) {
                if (producao.startsWith(var + "->")) {
                    String derivacao = producao.substring(producao.indexOf(">") + 1);
                    if (derivacao.contains(simbolo)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<String> getV() {
        return V;
    }

    public void setV(List<String> v) {
        V = v;
    }

    public List<String> getT() {
        return T;
    }

    public void setT(List<String> t) {
        T = t;
    }

    public List<String> getP() {
        return P;
    }

    public void setP(List<String> p) {
        P = p;
    }

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S = s;
    }
}
