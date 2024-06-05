package bizerba.scalevalidationreminder.service;


import bizerba.scalevalidationreminder.model.Store;
import bizerba.scalevalidationreminder.repository.StoreRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public StoreServiceImpl(StoreRepository storeRepository, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }


}
