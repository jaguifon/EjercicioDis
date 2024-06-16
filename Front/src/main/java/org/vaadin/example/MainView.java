package org.vaadin.example;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    Grid<Objeto> grid = new Grid<>(Objeto.class, false);
    public MainView(@Autowired GreetService service) {

        grid.addColumn(Objeto::getName).setHeader("Ship name");
        grid.addColumn(Objeto::getModel).setHeader("Ship model");
        grid.addColumn(Objeto::getCostInCredits).setHeader("Cost in credits");
        grid.addColumn(Objeto::getCrew).setHeader("Crew");
        grid.addColumn(Objeto::getCargoCapacity).setHeader("Cargo capacity");
        grid.addColumn(Objeto::getConsumables).setHeader("Consumables");
        grid.addColumn(Objeto::getHyperdriveRating).setHeader("Hyperdrive rating");
        grid.addColumn(Objeto::getStarshipClass).setHeader("Starship class");
        grid.addColumn(Objeto::getPilotsView).setHeader("Pilots");
        grid.addColumn(Objeto::getFilmsView).setHeader("Films");
        grid.addColumn(new NativeButtonRenderer<>("Generar", service::senPDFRequest)
        ).setHeader("Generar PDF");
        List<Objeto> objeto = service.getObjetos();
        grid.setItems(objeto);

        grid.setWidth("200%");
        grid.setWidthFull();


        add(grid);

    }

}
