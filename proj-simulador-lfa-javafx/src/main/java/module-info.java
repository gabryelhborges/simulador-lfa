module unoeste.fipp.gabryelborges.projsimuladorlfajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens unoeste.fipp.gabryelborges.projsimuladorlfajavafx to javafx.fxml;
    exports unoeste.fipp.gabryelborges.projsimuladorlfajavafx;
}