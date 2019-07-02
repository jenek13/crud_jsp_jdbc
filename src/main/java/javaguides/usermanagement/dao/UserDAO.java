package javaguides.usermanagement.dao;

import javaguides.usermanagement.model.User;
import javaguides.usermanagement.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements InterfaceDAO<User> {

	public final Connection connection;

	public UserDAO() {
		this.connection = DBHelper.getMysqlConnection();
	}

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, age) VALUES "
			+ " (?, ?);";
	private static final String SELECT_USER_BY_ID = "select id,name,age from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?, age= ? where id = ?;";




	@Override
	public void insertDAO(User user) {

		System.out.println(INSERT_USERS_SQL);
		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setInt(2, user.getAge());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}

	}

	@Override
	public User selectUser(int id) {
		User user = null;
		try (//Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				user = new User(id, name, age);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	@Override
	public List<User> selectAllUsers() {

		List<User> users = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				users.add(new User(id, name, age));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	@Override
	public boolean deleteModel(int id) throws SQLException {
		boolean rowDeleted;
		try (PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);//в делит запрос подставлем айдишку того юзера котрого надо удалить
			rowDeleted = statement.executeUpdate() > 0;//это когда удлил лжну тсрочк уи в бд пишется что одна строка афектед но не точно
		}
		return rowDeleted;

	}

	@Override
	public boolean updateModel(User user) throws SQLException {
		boolean rowUpdated;
		try (//Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getName());
			statement.setInt(2, user.getAge());
			statement.setInt(3, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}