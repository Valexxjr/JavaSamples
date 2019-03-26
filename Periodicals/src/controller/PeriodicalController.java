package controller;

import controller.comparators.CompByCost;
import controller.comparators.CompByPeriod;
import controller.strategies.SortByMax;
import controller.strategies.SortByMin;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import model.Periodical;
import model.RemoteControl;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The class {@code PeriodicalController} contains methods and fields
 * to manage the scene
 * @author Alexander Valai
 */

public class PeriodicalController {

    public TableView<Periodical> table;
    public TableColumn<Periodical, String> periodical;
    public TableColumn<Periodical, String> period;
    public TableColumn<Periodical, String> cost;
    public TableColumn<Periodical, String> publishingHouse;

    public RadioButton toggleAsc;
    public RadioButton toggleDesc;
    public RadioButton toggleCost;
    public RadioButton togglePeriod;

    private ToggleGroup tgArgument;
    private ToggleGroup tgOrder;

    private PeriodicalBinding pb = null;

    @FXML
    private void initialize() throws Exception{
        tgArgument = new ToggleGroup();
        tgOrder = new ToggleGroup();
        toggleCost.setToggleGroup(tgArgument);
        togglePeriod.setToggleGroup(tgArgument);
        toggleCost.setSelected(true);

        toggleAsc.setToggleGroup(tgOrder);
        toggleDesc.setToggleGroup(tgOrder);
        toggleAsc.setSelected(true);

        final Registry registry = LocateRegistry.getRegistry("localhost", 2099);
        RemoteControl control = (RemoteControl) registry.lookup("server.remoteControl");
        pb = control.getBinding();

        showPeriodicals();
    }

    @FXML
    private void resort() {
        if(toggleCost.isSelected()) {
            if(toggleAsc.isSelected()) {
                pb.setSorting(new SortByMin(new CompByCost()));
            }
            else {
                pb.setSorting(new SortByMax(new CompByCost()));
            }
        }
        else {
            if(toggleAsc.isSelected()) {
                pb.setSorting(new SortByMin(new CompByPeriod()));
            }
            else {
                pb.setSorting(new SortByMax(new CompByPeriod()));
            }
        }
        pb.sort();
        showPeriodicals();
    }

    private void showPeriodicals() {
        ObservableList<Periodical> post = FXCollections.observableArrayList();
        post.addAll(pb.getArray());
        table.setItems(post);
        periodical.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getName()));
        period.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPeriod().toString()));
        cost.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getCost())));
        publishingHouse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublishingHouse()));
    }

}
