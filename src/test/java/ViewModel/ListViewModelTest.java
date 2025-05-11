package ViewModel;

import Model.TourDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListViewModelTest {

    private ListViewModel viewModel;
    private StringProperty searchText;

    @BeforeEach
    void setUp() {
        searchText = new SimpleStringProperty("");
        viewModel = new ListViewModel(searchText);
    }

    @Test
    void testInitialDummyData() {
        assertEquals(3, viewModel.getFilteredTourDtos().size(), "Es sollten 3 Dummy-Touren vorhanden sein.");
    }

    @Test
    void testAddTour() {
        TourDto newTourDto = new TourDto("New Trip", "A", "B", 10.0, "1h", "desc", null);
        viewModel.addTour(newTourDto);

        assertEquals(4, viewModel.getFilteredTourDtos().size());
        assertEquals("3", newTourDto.getId()); // ID sollte der Index sein
    }

    @Test
    void testRemoveTour() {
        TourDto toRemove = viewModel.getFilteredTourDtos().get(0);
        viewModel.removeTour(toRemove);

        assertEquals(2, viewModel.getFilteredTourDtos().size());
        assertFalse(viewModel.getFilteredTourDtos().contains(toRemove));
    }

    @Test
    void testSearchFilter() {
        searchText.set("berlin");

        searchText.set("Get Out Of Berlin");
        assertEquals(1, viewModel.getFilteredTourDtos().size());

        searchText.set("sdkjfbsdifhguiz");
        assertEquals(0, viewModel.getFilteredTourDtos().size());

    }

    @Test
    void testSearchNoMatch() {
        searchText.set("xyz");

        assertEquals(0, viewModel.getFilteredTourDtos().size());
    }
}
