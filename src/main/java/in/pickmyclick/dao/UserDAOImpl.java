package in.pickmyclick.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import in.pickmyclick.controller.FileUploadController;
import in.pickmyclick.info.UserInfo;
import in.pickmyclick.modal.UserDto;

@Repository
public class UserDAOImpl implements UserDAO{

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
	   

	private JdbcTemplate jdbcTemplate;

	@Autowired 
	private PlatformTransactionManager platformTransactionManager;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public UserInfo getUserInfo(String username){
		String sql = "SELECT u.username name, u.password pass, a.authority role FROM "+
				"users u INNER JOIN authorities a on u.username=a.username WHERE "+
				"u.enabled =1 and u.username = ?";
		UserInfo userInfo = (UserInfo)jdbcTemplate.queryForObject(sql, new Object[]{username},
				new RowMapper<UserInfo>() {
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfo user = new UserInfo();
				user.setUsername(rs.getString("name"));
				logger.debug("-----------> "+ rs.getString("name"));
				logger.debug("-----------> "+ rs.getString("pass"));
				user.setPassword(rs.getString("pass"));
				user.setRole(rs.getString("role"));
				return user;
			}
		});
		return userInfo;
	}

	@Override
	public void save(UserDto user) {
		DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status=platformTransactionManager.getTransaction(paramTransactionDefinition );
		String insertUser = "insert into users (username, password,enabled,firstname,lastname,email,address,phone) values(?,?,?,?,?,?,?,?)";
		String userAuth = "insert into authorities (username,authority) values(?,?)";
		user.setEnabled(1); 
		Object[] args = new Object[] {user.getUserId(),user.getPassword(),user.getEnabled(),user.getFirstName(),user.getLastName(), user.getEmail(), user.getShippingAddress(),user.getMobileNumber()};
		try {
			int out = jdbcTemplate.update(insertUser, args);
			Object[] authObjects = new Object[] {user.getUserId(),"ROLE_USER"};
			int auth = jdbcTemplate.update(userAuth, authObjects);
			platformTransactionManager.commit(status);
			if(out !=0 && auth!=0){
				System.out.println("Employee saved with id="+user.getEmail());
			}
		}catch (Exception e) {
			System.out.println("Employee save failed with id="+user.getEmail());  
			platformTransactionManager.rollback(status);
		}
	}

	@Override
	public Boolean isUserExists(String email) {
		String sql = "SELECT count(*) FROM USERS WHERE email = ?";
		boolean result = false;
		int count = jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public void update(UserDto userDto) {
		String query = "update user set enabled=?, password=? where username=?";
		Object[] args = new Object[] {userDto.getEnabled(), userDto.getPassword(), userDto.getEmail()};
		int out = jdbcTemplate.update(query, args);
		if(out !=0){
			System.out.println("User saved with id="+userDto.getEmail());
		}else System.out.println("User save failed with id="+userDto.getEmail());
	}
	/*		 
	    @Override
	    public void deleteById(int id) {

	        String query = "delete from Employee where id=?";
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	        int out = jdbcTemplate.update(query, id);
	        if(out !=0){
	            System.out.println("Employee deleted with id="+id);
	        }else System.out.println("No Employee found with id="+id);
	    }

	    @Override
	    public List<UserDto> getAll() {
	        String query = "select id, name, role from Employee";
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	        List<UserDto> empList = new ArrayList<Employee>();

	        List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);

	        for(Map<String,Object> empRow : empRows){
	        	UserDto emp = new UserDto();
	            emp.setId(Integer.parseInt(String.valueOf(empRow.get("id"))));
	            emp.setName(String.valueOf(empRow.get("name")));
	            emp.setRole(String.valueOf(empRow.get("role")));
	            empList.add(emp);
	        }
	        return empList;
	    }*/
} 
