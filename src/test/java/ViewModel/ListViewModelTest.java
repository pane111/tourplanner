package ViewModel;

import Model.Tour;
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
        assertEquals(3, viewModel.getFilteredTours().size(), "Es sollten 3 Dummy-Touren vorhanden sein.");
    }

    @Test
    void testAddTour() {
        Tour newTour = new Tour("New Trip", "A", "B", 10.0, "1h", "desc", null);
        viewModel.addTour(newTour);

        assertEquals(4, viewModel.getFilteredTours().size());
        assertEquals("3", newTour.getId()); // ID sollte der Index sein
    }

    @Test
    void testRemoveTour() {
        Tour toRemove = viewModel.getFilteredTours().get(0);
        viewModel.removeTour(toRemove);

        assertEquals(2, viewModel.getFilteredTours().size());
        assertFalse(viewModel.getFilteredTours().contains(toRemove));
    }

    @Test
    void testSearchFilter() {
        searchText.set("berlin");

        searchText.set("Get Out Of Berlin");
        assertEquals(1, viewModel.getFilteredTours().size());

        searchText.set("sdkjfbsdifhguiz");
        assertEquals(0, viewModel.getFilteredTours().size());

    }

    @Test
    void testSearchNoMatch() {
        searchText.set("xyz");

        assertEquals(0, viewModel.getFilteredTours().size());
    }
}
