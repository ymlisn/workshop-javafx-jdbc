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
import model.entities.Seller;
import model.exceptions.ValidationException;
import model.services.DepartmentService;
import model.services.SellerService;

import java.net.URL;
import java.util.*;

public class SellerFormController implements Initializable {


    private SellerService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    private Seller entity;

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
        catch(ValidationException e){

            setErrorMessages(e.getErrors());

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

    private Seller getFormData() {



        Seller obj = new Seller();

        ValidationException exception = new ValidationException("Validation error");

        obj.setId(Utils.tryParseToInt(textId.getText()));

        if(textName.getText()==null || textName.getText().trim().equals("")){

            exception.addErrors("name","Field cant be empty");

        }
        obj.setName(textName.getText());

        if(exception.getErrors().size()>0){
            throw exception;

        }

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

    public void setSeller(Seller entity) {
        this.entity = entity;
    }

    public void setSellerService(SellerService service) {
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
        textName.setText(entity.getName());

    }

    private void setErrorMessages(Map<String,String> error){

        Set<String> fields = error.keySet();

        if(fields.contains("name")){

            labelErrorName.setText(error.get("name"));

        }


    }

}
