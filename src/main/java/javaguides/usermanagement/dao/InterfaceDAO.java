package javaguides.usermanagement.dao;

import javaguides.usermanagement.model.User;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<Entity> {
     void insertDAO(Entity model);
    Entity selectUser(int id);
     List<Entity> selectAllUsers();
     boolean deleteModel(int id) throws SQLException;
     boolean updateModel(Entity model) throws SQLException;
}
