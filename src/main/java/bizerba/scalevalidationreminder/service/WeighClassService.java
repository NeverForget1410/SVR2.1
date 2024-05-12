package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.WeightClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface WeighClassService {

    Page<WeightClass> getAllWeightClassPaginated(Pageable pageable);

    WeightClass getWeightClassById(Integer idWeightClass);

    void saveWeightClass(WeightClass weightClass, Principal principal);

    void deleteWeightClassById(Integer idWeightClass);


}
