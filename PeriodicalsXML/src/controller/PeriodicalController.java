package controller;

import controller.PeriodicalBinding;
import controller.comparators.CompByCost;
import controller.comparators.CompByPeriod;
import controller.factories.PeriodicalFactory;
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
import model.Period;
import model.Periodical;

import java.util.ArrayList;

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
    private void initialize() {
        tgArgument = new ToggleGroup();
        tgOrder = new ToggleGroup();
        toggleCost.setToggleGroup(tgArgument);
        togglePeriod.setToggleGroup(tgArgument);
        toggleCost.setSelected(true);

        toggleAsc.setToggleGroup(tgOrder);
        toggleDesc.setToggleGroup(tgOrder);
        toggleAsc.setSelected(true);

        initBinding();
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

    private void initBinding() {
        PeriodicalFactory pf1 = PeriodicalFactory.getFactory("magazine");
        PeriodicalFactory pf2 = PeriodicalFactory.getFactory("newspaper");
        ArrayList<Periodical> ap = new ArrayList<Periodical>();
        ap.add(pf1.createPrinted(2.4, Period.DAILY,"BelPrint", "SB",
                20, 10000, 122, 1000));
        ap.add(pf2.createPrinted(4.1, Period.MONTHLY,"Eksmo", "Unbelievable",
                34, 5000, 10, 230));
        ap.add(pf1.createOnline(1.1,"TUT", "www.tut.by",
                34453.2, 154, 82000));
        ap.add(pf2.createOnline(5.1,"ДАЛС", "www.dals.com",
                11.8, 14, 50));
        ap.add(pf1.createPrinted(7.59, Period.DAILY,"MAIL", "Daily Mail",
                20, 100000, 582, 50000));
        ap.add(pf2.createPrinted(8.0, Period.MONTHLY,"BritPr", "The Guardian",
                80, 150000, 284, 150500));
        ap.add(pf2.createPrinted(8.94, Period.ANNUAL,"bell", "AnnualCalendar",
                370, 990000, 16, 0));
        ap.add(pf1.createOnline(0.89,"belat", "www.naviny.by",
                3445.1, 275, 8000));
        ap.add(pf2.createOnline(0.17,"mail.ru", "www.mail.ru",
                15123.9, 1230, 1434529));

        Periodical[] arr = new Periodical[ap.size()];
        for(int i = 0; i < ap.size(); i++) {
            arr[i] = ap.get(i);
        }
        pb = new PeriodicalBinding(arr, new SortByMax(new CompByCost()));
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
