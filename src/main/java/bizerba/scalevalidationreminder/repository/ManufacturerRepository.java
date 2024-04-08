package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ManufacturerRepository extends JpaRepository <Manufacturer, Integer>{
}
