package habit.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRoleDao extends CrudRepository<UserRole, Integer> {
    public List<String> findRoleByUsersEmail(String email);
    }

