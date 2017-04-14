package controller.command.impl;

import bean.EmployeeHR;
import controller.command.Command;
import controller.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.EmployeeService;
import service.JobVacancyService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by irinaleibutina on 4/11/17.
 */
public class AddAdmin implements Command {
    private static final Logger logger = LogManager.getLogger(AddAdmin.class.getName());

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL ="email";
    private static final String PHONE = "phone";
    private static final String WORK_ID = "workId";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ATTR_ERROR = "error";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EmployeeService employeeService = serviceFactory.getEmployeeService();

        EmployeeHR employeeHR = new EmployeeHR();
        employeeHR.setName(request.getParameter(NAME));
        employeeHR.setSurname(request.getParameter(SURNAME));
        employeeHR.setEmail(request.getParameter(EMAIL));
        employeeHR.setPhoneNumber(request.getParameter(PHONE));
        employeeHR.setWorkId(request.getParameter(WORK_ID));
        employeeHR.setLogin(request.getParameter(LOGIN));
        employeeHR.setPassword(request.getParameter(PASSWORD));

        try {
            employeeService.employeeRegistration(employeeHR);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
