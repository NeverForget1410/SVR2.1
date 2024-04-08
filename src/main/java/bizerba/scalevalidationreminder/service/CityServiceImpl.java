package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.City;
import bizerba.scalevalidationreminder.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Override
    public Page<City> getAllCityPaginated(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public List<City> getAllCity(){return cityRepository.findAll();}

}
