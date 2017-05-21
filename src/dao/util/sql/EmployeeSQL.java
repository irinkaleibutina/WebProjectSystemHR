package dao.util.sql;

/**
 *The class contains query strings to work with employee info
 */
public final class EmployeeSQL {
    public static final String ADMIN_REGISTRATION = "insert into mydb.employeeHR(empl_id,empl_name, empl_surname," +
            " password, login,phone_number, email, workId) values(LAST_INSERT_ID(),?, ?,SHA2(?,256),?, ?, ?, ?)";

    public static final String DELETE_ADMIN = "UPDATE mydb.employeeHR set current_status = 'N' where login=?";
    public static final String ADMIN_INFORMATION = "SELECT * FROM mydb.employeeHR";
    public static final String APPLICANT = "SELECT * FROM mydb.applicant WHERE login=?";
    public static final String ADMIN = "SELECT * FROM mydb.employeeHR WHERE login=? AND password=SHA2(?,256)";

    public static final String UPDATE_ADMIN_INFO = "UPDATE mydb.employeeHR SET " +
            "empl_name = ?, empl_surname = ?, email= ?, phone_number = ?, workId =? WHERE empl_id=?";
}
