module org.sistema.reserva {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.sistema.reserva to javafx.fxml;
    exports org.sistema.reserva;
    exports org.sistema.reserva.controllersFx;
    opens org.sistema.reserva.controllersFx to javafx.fxml;
    exports org.sistema.reserva.controllersFx.Acomodacao;
    opens org.sistema.reserva.controllersFx.Acomodacao to javafx.fxml;
    opens org.sistema.reserva.entity to javafx.base;
    exports org.sistema.reserva.validations;
    opens org.sistema.reserva.validations to javafx.fxml;
    exports org.sistema.reserva.controllersFx.Tela_Principal;
    opens org.sistema.reserva.controllersFx.Tela_Principal to javafx.fxml;
    exports org.sistema.reserva.controllersFx.Funcionario;
    opens org.sistema.reserva.controllersFx.Funcionario to javafx.fxml;
}