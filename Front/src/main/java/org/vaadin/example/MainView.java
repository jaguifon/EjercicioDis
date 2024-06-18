package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
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
        grid.addColumn(new NativeButtonRenderer<>("Editar Nave", this::showEditDialog)
        ).setHeader("Editar");
        List<Objeto> objeto = service.getObjetos();
        grid.setItems(objeto);
        grid.setWidthFull();


        add(grid);

    }
    private void showEditDialog(Objeto ship) {
        Dialog dialog = new Dialog();
        FormLayout formLayout = new FormLayout();

        TextField nameField = new TextField("Name");
        nameField.setValue(ship.getName());
        formLayout.add(nameField);

        TextField modelField = new TextField("Model");
        modelField.setValue(ship.getModel());
        formLayout.add(modelField);

        TextField costInCreditsField = new TextField("Cost in Credits");
        costInCreditsField.setValue(ship.getCostInCredits());
        formLayout.add(costInCreditsField);

        TextField crewField = new TextField("Crew");
        crewField.setValue(ship.getCrew());
        formLayout.add(crewField);

        TextField cargoCapacityField = new TextField("Cargo Capacity");
        cargoCapacityField.setValue(ship.getCargoCapacity());
        formLayout.add(cargoCapacityField);

        TextField consumablesField = new TextField("Consumables");
        consumablesField.setValue(ship.getConsumables());
        formLayout.add(consumablesField);

        TextField hyperdriveRatingField = new TextField("Hyperdrive Rating");
        hyperdriveRatingField.setValue(ship.getHyperdriveRating());
        formLayout.add(hyperdriveRatingField);

        TextField starshipClassField = new TextField("Starship Class");
        starshipClassField.setValue(ship.getStarshipClass());
        formLayout.add(starshipClassField);

        TextArea pilotsArea = new TextArea("Pilots (comma separated)");
        pilotsArea.setValue(String.join(", ", ship.getPilots()));
        formLayout.add(pilotsArea);

        TextArea filmsArea = new TextArea("Films (comma separated)");
        filmsArea.setValue(String.join(", ", ship.getFilms()));
        formLayout.add(filmsArea);

        Button saveButton = new Button("Save", event -> {
            // Obtener los valores editados o mantener los existentes si el campo está vacío
            String editedName = nameField.isEmpty() ? ship.getName() : nameField.getValue();
            String editedModel = modelField.isEmpty() ? ship.getModel() : modelField.getValue();
            String editedCostInCredits = costInCreditsField.isEmpty() ? ship.getCostInCredits() : costInCreditsField.getValue();
            String editedCrew = crewField.isEmpty() ? ship.getCrew() : crewField.getValue();
            String editedCargoCapacity = cargoCapacityField.isEmpty() ? ship.getCargoCapacity() : cargoCapacityField.getValue();
            String editedConsumables = consumablesField.isEmpty() ? ship.getConsumables() : consumablesField.getValue();
            String editedHyperdriveRating = hyperdriveRatingField.isEmpty() ? ship.getHyperdriveRating() : hyperdriveRatingField.getValue();
            String editedStarshipClass = starshipClassField.isEmpty() ? ship.getStarshipClass() : starshipClassField.getValue();
            String[] editedPilots = pilotsArea.isEmpty() ? ship.getPilots() : pilotsArea.getValue().split(",\\s*");
            String[] editedFilms = filmsArea.isEmpty() ? ship.getFilms() : filmsArea.getValue().split(",\\s*");

// Crear un nuevo objeto Ship con los valores editados o existentes
            Objeto datosEditados = new Objeto(
                    ship.getName(),  // Assuming Ship has an ID
                    editedModel,
                    editedCostInCredits,
                    editedCrew,
                    editedCargoCapacity,
                    editedConsumables,
                    editedHyperdriveRating,
                    editedStarshipClass,
                    editedPilots,
                    editedFilms
            );

            // Llama al servicio para editar la nave con los datos editados
            GreetService dataService = new GreetService();
            Objeto updatedObjeto = dataService.editObjetos(datosEditados);  // Actualiza el grid con la lista actualizada de naves
            List<Objeto> editedObjetos = dataService.getObjetos();
            grid.setItems(editedObjetos);
            dialog.close();

        });


        formLayout.add(saveButton);
        dialog.add(formLayout);
        dialog.open();

    }

}
