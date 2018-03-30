package habit.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private int userRoleId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_user_role",
            joinColumns = { @JoinColumn(name = "user_role_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> users = new HashSet<User>();

    private String role;

    public UserRole(){}

    public UserRole(User user, String role){
        this.role = role;
        users.add(user);
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRoles(String role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Set<User> getUsers () {
            return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
