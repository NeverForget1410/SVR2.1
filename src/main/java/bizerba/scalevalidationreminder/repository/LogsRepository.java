package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Logs,Integer> {
}
