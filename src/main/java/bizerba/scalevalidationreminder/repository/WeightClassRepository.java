package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.WeightClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightClassRepository extends JpaRepository<WeightClass,Integer> {
}
