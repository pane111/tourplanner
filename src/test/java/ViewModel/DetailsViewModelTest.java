package ViewModel;

import Model.TourDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetailsViewModelTest {

    private DetailsViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new DetailsViewModel();
    }

    @Test
    void testSetCurTour_setsAllPropertiesCorrectly() {
        TourDto tour = new TourDto(
                "Beach Trip", "Vienna", "Barcelona",
                1234.5678, "7260", // 2h 1min
                "Summer adventure", 77L
        );

        viewModel.setCurTour(tour);

        assertEquals("Beach Trip", viewModel.getName().get());
        assertEquals("Vienna", viewModel.getFrom().get());
        assertEquals("Barcelona", viewModel.getTo().get());
        assertEquals("1234,57 km", viewModel.getDistance().get());
        assertEquals("2 hours, 1 minutes", viewModel.getEstimatedTime().get());
        assertEquals("Summer adventure", viewModel.getDescription().get());
        assertEquals(77L, viewModel.getId().get());
    }

    @Test
    void testSetCurTour_withNullInput_doesNothing() {
        viewModel.setCurTour(null);

        assertNull(viewModel.getName().get());
        assertNull(viewModel.getFrom().get());
        assertNull(viewModel.getTo().get());
        assertNull(viewModel.getDistance().get());
        assertNull(viewModel.getEstimatedTime().get());
        assertNull(viewModel.getDescription().get());
        assertEquals(0L, viewModel.getId().get());
    }

    @Test
    void testUnset_clearsAllTextProperties() {
        TourDto tour = new TourDto(
                "Test", "A", "B", 100.0, "3600", "Desc", 1L
        );
        viewModel.setCurTour(tour);
        viewModel.unset();

        assertNull(viewModel.getName().get());
        assertNull(viewModel.getFrom().get());
        assertNull(viewModel.getTo().get());
        assertNull(viewModel.getDistance().get());
        assertNull(viewModel.getEstimatedTime().get());
        assertNull(viewModel.getDescription().get());
        assertEquals(1L, viewModel.getId().get()); // ID is not cleared
    }

    @Test
    void testSetCurTour_estimatedTimeExactFormatting() {
        TourDto tour = new TourDto("Test", "X", "Y", 0.0, "3723", "Some desc", 88L);
        DetailsViewModel viewModel = new DetailsViewModel();
        viewModel.setCurTour(tour);

        assertEquals("1 hours, 2 minutes", viewModel.getEstimatedTime().get());
    }

    @Test
    void testSetCurTour_distanceIsRoundedCorrectly() {
        TourDto tour = new TourDto("Test", "From", "To", 42.424242, "3600", "desc", 1L);
        DetailsViewModel viewModel = new DetailsViewModel();
        viewModel.setCurTour(tour);

        assertEquals("42,42 km", viewModel.getDistance().get()); // or "42.42 km" depending on your Locale
    }



}
