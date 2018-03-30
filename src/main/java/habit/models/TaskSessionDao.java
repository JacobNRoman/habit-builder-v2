package habit.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskSessionDao extends CrudRepository<TaskSession, Integer> {
    public List<TaskSession> findAllByOrderByIdDesc();
}
