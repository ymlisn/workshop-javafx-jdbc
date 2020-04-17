package gui;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {


    @FXML
    private TextField textId;

    @FXML
    private TextField textName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    @FXML
    public void onBtSaveAction(){

     System.out.println("test");
    }

    @FXML
    public void onBtCancelAction(){

        System.out.println("test1");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    initializeNodes();

    }
        private void initializeNodes() {

            Constraints.setTextFieldInteger(textId);
            Constraints.setTextFieldMaxLength(textName,50);




    }
}