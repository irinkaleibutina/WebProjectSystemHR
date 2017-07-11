package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.EmployeeService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;
import by.epam.systemHR.bean.EmployeeHR;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            request.setAttribute(ParametersName.ADMINS, employeeHRS);

            if (employeeHRS.isEmpty()) {
                request.setAttribute(ATTR_FAIL, "Admins not found");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
                requestDispatcher.forward(request, response);
            }

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
