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
}
