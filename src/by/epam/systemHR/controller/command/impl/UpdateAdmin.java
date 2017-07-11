package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.bean.EmployeeHR;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.service.EmployeeService;
import by.epam.systemHR.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Instance of {@link Command}
 */
public class UpdateAdmin implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateAdmin.class.getName());

    private static final String SHOW_PAGE = "Controller?command=get_applicant";

    /**
     * Performs a service level call to update admin info
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeHR employeeHR = new EmployeeHR();
        employeeHR = (EmployeeHR) request.getSession(true).getAttribute(ParametersName.EMPLOYEE);

        employeeHR.setName(request.getParameter(ParametersName.NAME));
        employeeHR.setSurname(request.getParameter(ParametersName.SURNAME));
        employeeHR.setEmail(request.getParameter(ParametersName.EMAIL));
        employeeHR.setPhoneNumber(request.getParameter(ParametersName.PHONE));
        employeeHR.setWorkId(request.getParameter(ParametersName.WORK_ID));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EmployeeService employeeService = serviceFactory.getEmployeeService();
        try {
            employeeService.updateEmployeeInfo(employeeHR);


//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
//            requestDispatcher.forward(request, response);
//

            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
