package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinate {
    double latitude;
    double longitude;
    public String orsFormat()
    {
        return longitude+","+latitude;
    }

    public Coordinate(String ors)
    {
        String[] parts = ors.split(",");

        if (parts.length == 2) {
            double lon = Double.parseDouble(parts[0].trim());
            double lat = Double.parseDouble(parts[1].trim());

            this.longitude = lon;
            this.latitude = lat;
        } else {
            System.err.println("Invalid coordinate format");
        }
    }

}
