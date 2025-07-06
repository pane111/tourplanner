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
    void testCreateTour_withValidInput_shouldReturnTourDto() {
        viewModel.getName().set("Testtour");
        viewModel.getFrom().set("Wien");
        viewModel.getTo().set("Salzburg");
        viewModel.getDescription().set("Eine schöne Route.");

        TourDto tour = viewModel.createTour();

        assertNotNull(tour);
        assertEquals("Testtour", tour.getName());
        assertEquals("Wien", tour.getFrom());
        assertEquals("Salzburg", tour.getTo());
        assertEquals("Eine schöne Route.", tour.getDescription());
    }

    @Test
    void testCreateTour_withEmptyName_shouldReturnNull() {
        viewModel.getFrom().set("Wien");
        viewModel.getTo().set("Salzburg");
        viewModel.getDescription().set("Route");

        TourDto tour = viewModel.createTour();

        assertNull(tour);
    }

    @Test
    void testCreateTourWithId_withValidInput_shouldReturnTourDtoWithId() {
        viewModel.getName().set("Tour 1");
        viewModel.getFrom().set("Start");
        viewModel.getTo().set("End");
        viewModel.getDescription().set("Beschreibung");
        viewModel.getId().set("123");

        TourDto tour = viewModel.createTourWithId();

        assertNotNull(tour);
        assertEquals("Tour 1", tour.getName());
        assertEquals("Start", tour.getFrom());
        assertEquals("End", tour.getTo());
        assertEquals("Beschreibung", tour.getDescription());
        assertEquals(123L, tour.getId());
    }

    @Test
    void testCreateTourWithId_withEmptyField_shouldReturnNull() {
        viewModel.getName().set("Tour 1");
        viewModel.getFrom().set("Start");
        viewModel.getDescription().set("Beschreibung");
        viewModel.getId().set("123");

        TourDto tour = viewModel.createTourWithId();

        assertNull(tour);
    }

    @Test
    void testCreateTourWithId_withInvalidId_shouldThrowException() {
        viewModel.getName().set("Tour 1");
        viewModel.getFrom().set("Start");
        viewModel.getTo().set("End");
        viewModel.getDescription().set("Beschreibung");
        viewModel.getId().set("ABC"); // Invalid Long

        assertThrows(NumberFormatException.class, () -> {
            viewModel.createTourWithId();
        });
    }

    @Test
    void testCreateTour_withAllFieldsEmpty_shouldReturnNull() {
        AddEditViewModel viewModel = new AddEditViewModel();
        assertNull(viewModel.createTour());
    }

}
