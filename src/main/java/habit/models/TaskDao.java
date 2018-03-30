package habit.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@javax.transaction.Transactional
public interface TaskDao extends CrudRepository<Task, Integer> {
}
