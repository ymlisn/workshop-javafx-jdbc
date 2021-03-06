package gui;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {


    @FXML
    private MenuItem menuItemVendedor;

    @FXML
    private MenuItem menuItemDepartamento;

    @FXML
    private MenuItem menuItemSobre;

    @FXML
    public void onMenuItemVendedorAction(){

        loadView("/gui/SellerList.fxml",(SellerListController controller) -> {
            controller.setSellerService(new SellerService());
            controller.updateTableView();
        });


    }

    @FXML
    public void onMenuItemDepartamentoAction(){

        loadView("/gui/DepartamentList.fxml",(DepartmentListController controller) -> {
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        });


    }

    @FXML
    public void onMenuItemSobreAction(){

        loadView("/gui/About.fxml", x -> {});


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private synchronized<T> void loadView(String absoluteName, Consumer<T> initializingAction){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newvBox = loader.load();

            Scene mainScene = Main.mainScene;
            VBox mainVBox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newvBox.getChildren());

          T controller =  loader.getController();
          initializingAction.accept(controller);

        }

        catch (IOException e){

            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);


        }
    }




}
