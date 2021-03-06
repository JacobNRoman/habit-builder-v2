package habit.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@javax.transaction.Transactional
public interface TaskDao extends CrudRepository<Task, Integer> {
    List<Task> findAllByUser(User user);
}
