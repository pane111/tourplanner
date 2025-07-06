package ViewModel;

import Controller.Mediator;
import Model.TourLogDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddEditLogViewModelTest {

    private AddEditLogViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new AddEditLogViewModel();
        Mediator.getInstance().selectedTourId = new SimpleLongProperty(42L);
    }

    private void fillValidData() {
        viewModel.getDate().set("12/03/2025");
        viewModel.getComment().set("Nice weather");
        viewModel.getDifficulty().set("Medium");
        viewModel.getDistance().set("10km");
        viewModel.getTime().set("2h");
        viewModel.getRating().set("4");
    }

    @Test
    void testCreateLog_withValidData_shouldReturnLog() {
        fillValidData();
        TourLogDto result = viewModel.createLog();
        assertNotNull(result);
        assertEquals("12/03/2025", result.getDate());
        assertEquals("Nice weather", result.getComment());
        assertEquals("Medium", result.getDifficulty());
        assertEquals("10km", result.getDistance());
        assertEquals("2h", result.getTime());  // <--- CORRECTED
        assertEquals(4, result.getRating());
        assertEquals(42L, result.getTourId());
    }

    @Test
    void testCreateLog_withEmptyField_shouldReturnNull() {
        fillValidData();
        viewModel.getTime().set("");  // One required field is empty
        assertNull(viewModel.createLog());
    }

    @Test
    void testCreateLog_withInvalidDateFormat_shouldReturnNull() {
        fillValidData();
        viewModel.getDate().set("2025-03-12");  // Invalid format
        assertNull(viewModel.createLog());
    }

    @Test
    void testCreateLog_withTooHighRating_shouldClampTo5() {
        fillValidData();
        viewModel.getRating().set("10");  // Too high
        TourLogDto result = viewModel.createLog();
        assertNotNull(result);
        assertEquals(5, result.getRating());
    }

    @Test
    void testCreateLog_withNegativeRating_shouldClampTo0() {
        fillValidData();
        viewModel.getRating().set("-3");  // Negative
        TourLogDto result = viewModel.createLog();
        assertNotNull(result);
        assertEquals(0, result.getRating());
    }

    @Test
    void testCreateLog_withInvalidRating_shouldReturnNull() {
        fillValidData();
        viewModel.getRating().set("bad");  // Not a number
        assertNull(viewModel.createLog());
    }

    @Test
    void testCreateLog_withId_shouldSetLogId() {
        fillValidData();
        viewModel.getId().set("99");
        TourLogDto result = viewModel.createLog();
        assertNotNull(result);
        assertEquals(99L, result.getLogId());
    }

    @Test
    void testCreateLog_withoutId_shouldNotSetLogId() {
        fillValidData();
        TourLogDto result = viewModel.createLog();
        assertNotNull(result);
        assertNull(result.getLogId());
    }

    @Test
    void testCreateLog_withMissingRating_shouldReturnNull() {
        AddEditLogViewModel viewModel = new AddEditLogViewModel();
        Mediator.getInstance().selectedTourId = new SimpleLongProperty(1L);

        viewModel.getDate().set("10/10/2025");
        viewModel.getComment().set("Test");
        viewModel.getDifficulty().set("Easy");
        viewModel.getDistance().set("5km");
        viewModel.getTime().set("1h");

        // Rating is not set
        assertNull(viewModel.createLog());
    }

}
