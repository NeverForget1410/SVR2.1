package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.City;
import bizerba.scalevalidationreminder.repository.CityRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    public CityServiceImpl(CityRepository cityRepository, UserRepository userRepository) {
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<City> getAllCityPaginated(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public List<City> getAllCity(){return cityRepository.findAll();}

    @Override
    public City getCityById(Integer idCity) {
        return cityRepository.findById(idCity)
                .orElseThrow(() -> new RuntimeException("City not found" + idCity) );
    }

    @Override
    public void saveCity(City city, Principal principal) {
        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresent(user -> {
            Integer idUser = user.getIdUser();

            if (city.getIdCity() == null) {
                city.setAddedId(idUser);
                city.setDateAddition(new Date());
                city.setActive(1);
            } else {
                City existingCity = getCityById(city.getIdCity());
                city.setAddedId(existingCity.getAddedId());
                city.setDateAddition(existingCity.getDateAddition());
                city.setActive(existingCity.getActive());
            }
            city.setModifiedId(idUser);
            city.setDateModyfication(new Date());
        });
        this.cityRepository.save(city);
    }

    @Override
    public void deleteCityById(Integer idCity) {
        this.cityRepository.deleteById(idCity);

    }

}
