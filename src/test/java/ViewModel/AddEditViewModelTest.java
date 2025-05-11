package ViewModel;

import Model.TourDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddEditViewModelTest {

    private AddEditViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new AddEditViewModel();
    }

    @Test
    void testCreateTourWithValidInput() {
        viewModel.getName().set("Testtour");
        viewModel.getFrom().set("Wien");
        viewModel.getTo().set("Salzburg");
        viewModel.getDistance().set("300");
        viewModel.getEstimatedTime().set("5h");
        viewModel.getDescription().set("Eine schöne Route.");
        viewModel.getId().set("T001");

        TourDto tourDto = viewModel.createTour();

        assertNotNull(tourDto);
        assertEquals("Testtour", tourDto.getName());
        assertEquals("Wien", tourDto.getFrom());
        assertEquals("Salzburg", tourDto.getTo());
        assertEquals(300.0, tourDto.getDistance());
        assertEquals("5h", tourDto.getEstimatedTime());
        assertEquals("Eine schöne Route.", tourDto.getDescription());
        assertEquals("T001", tourDto.getId());
    }

    @Test
    void testCreateTourWithEmptyFields() {
        viewModel.getName().set(""); // leer!
        viewModel.getFrom().set("Wien");
        viewModel.getTo().set("Salzburg");
        viewModel.getDistance().set("300");
        viewModel.getEstimatedTime().set("5h");
        viewModel.getDescription().set("Beschreibung");
        viewModel.getId().set("T002");

        TourDto tourDto = viewModel.createTour();

        assertNull(tourDto); // sollte fehlschlagen
    }

    @Test
    void testCreateTourWithInvalidDistance() {
        viewModel.getName().set("Fehlertour");
        viewModel.getFrom().set("Graz");
        viewModel.getTo().set("Linz");
        viewModel.getDistance().set("abc"); // ungültig!
        viewModel.getEstimatedTime().set("3h");
        viewModel.getDescription().set("Irgendwas");
        viewModel.getId().set("T003");

        TourDto tourDto = viewModel.createTour();

        assertNull(tourDto); // Fehler wegen ungültiger Zahl
    }
}
