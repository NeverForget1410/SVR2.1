package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CityService {

    Page<City> getAllCityPaginated(Pageable pageable);

    List<City> getAllCity();
}
