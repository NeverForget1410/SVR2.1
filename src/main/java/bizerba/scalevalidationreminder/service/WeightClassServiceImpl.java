package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.WeightClass;
import bizerba.scalevalidationreminder.repository.UserRepository;
import bizerba.scalevalidationreminder.repository.WeightClassRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;

@Service
public class WeightClassServiceImpl implements WeighClassService{

    private final WeightClassRepository weightClassRepository;
    private final UserRepository userRepository;


    public WeightClassServiceImpl(WeightClassRepository weightClassRepository,UserRepository userRepository) {
        this.userRepository = userRepository;
        this.weightClassRepository = weightClassRepository;
    }


    @Override
    public Page<WeightClass> getAllWeightClassPaginated(Pageable pageable) {
        return weightClassRepository.findAll(pageable);
    }

    @Override
    public WeightClass getWeightClassById(Integer idWeightClass) {
        return weightClassRepository.findById(idWeightClass)
                .orElseThrow(()-> new RuntimeException("Weight class not found" + idWeightClass));
    }

    @Override
    @Transactional
    public void saveWeightClass(WeightClass weightClass, Principal principal) {
        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresent(user -> {
            Integer idUser = user.getIdUser();

            if (weightClass.getIdWeightClass() == null){
                weightClass.setAddedId(idUser);
                weightClass.setDateAddition(new Date());
                weightClass.setActive(1);
            } else {
                WeightClass existingWeightClass = getWeightClassById(weightClass.getIdWeightClass());
                weightClass.setAddedId(existingWeightClass.getAddedId());
                weightClass.setDateAddition(existingWeightClass.getDateAddition());
                weightClass.setActive(existingWeightClass.getActive());
            }
            weightClass.setModifiedId(idUser);
            weightClass.setDateModyfication(new Date());
        });
        this.weightClassRepository.save(weightClass);
    }

    @Override
    @Transactional
    public void deleteWeightClassById(Integer idWeightClass) {
        this.weightClassRepository.deleteById(idWeightClass);
    }
}
