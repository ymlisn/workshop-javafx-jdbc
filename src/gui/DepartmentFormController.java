package gui;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {


    private DepartmentService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    private Department entity;

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
    public void onBtSaveAction(ActionEvent event){

        if(entity==null){

            throw new IllegalStateException("Entity was null");
        }

        if(service==null){

            throw new IllegalStateException("Service was null");
        }

        try {
            entity = getFormData();
            service.saveOrUpdate(entity);
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        }
    catch (DbException e){
        Alerts.showAlert("Error saving object",null,e.getMessage(), Alert.AlertType.ERROR);

    }
    }

    private void notifyDataChangeListeners() {

        for (DataChangeListener listener: dataChangeListeners
             ) {
            listener.onDataChanged();

        }

    }

    private Department getFormData() {

        Department obj = new Department();

        obj.setId(Utils.tryParseToInt(textId.getText()));
        obj.setNome(textName.getText());

        return obj;

    }

    @FXML
    public void onBtCancelAction(ActionEvent event){

        Utils.currentStage(event).close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    initializeNodes();

    }
        private void initializeNodes() {

            Constraints.setTextFieldInteger(textId);
            Constraints.setTextFieldMaxLength(textName,50);




    }

    public void setDepartment(Department entity) {
        this.entity = entity;
    }

    public void setDepartmemtService(DepartmentService service) {
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener){

        dataChangeListeners.add(listener);

    }

    public void updateFormData(){

        if(entity==null){

            throw new  IllegalStateException("Entity was null");

        }

        textId.setText(String.valueOf(entity.getId()));
        textName.setText(entity.getNome());

    }

}
