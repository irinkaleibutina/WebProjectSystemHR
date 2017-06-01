package controller.command.impl;

import bean.EmployeeHR;
import bean.JobVacancy;
import controller.command.Command;
import controller.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.EmployeeService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class ShowAdmins implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAdmins.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/admins.jsp";
    private static final String ATTR_FAIL = "fail_take_admins";

    /**
     * Performs a service level call to get all admins
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ServiceFactory factory = ServiceFactory.getInstance();
        EmployeeService employeeService = factory.getEmployeeService();
        try {
            List<EmployeeHR> employeeHRS = employeeService.getEmployees();
            request.setAttribute(ADMINS, employeeHRS);

            if (employeeHRS.isEmpty()) {
                request.setAttribute(ATTR_FAIL, "Admins not found");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
                requestDispatcher.forward(request, response);
            }

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
