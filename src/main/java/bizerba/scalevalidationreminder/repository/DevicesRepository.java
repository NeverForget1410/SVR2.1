package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.Devices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DevicesRepository extends JpaRepository<Devices,Integer> {


    @Query("SELECT d FROM Devices d WHERE d.store in (SELECT uhs.store FROM UserHasStore uhs WHERE uhs.user.idUser = :idUser)")
    Page<Devices> getAllDevicesForUser(Pageable pageable, @Param("idUser") Integer idUser);

}
