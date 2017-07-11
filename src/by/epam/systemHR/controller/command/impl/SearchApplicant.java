package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.Applicant;
import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.ApplicantService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;
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
public class SearchApplicant implements Command {
    private static final Logger logger = LogManager.getLogger(SearchApplicant.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/concrete_applicant.jsp";

    /**
     * Performs a service level call to search applicant
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter(ParametersName.NAME);
        String surname = request.getParameter(ParametersName.SURNAME);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();

        try {
            List<Applicant> applicants = applicantService.searchApplicant(name, surname);
            request.setAttribute(ParametersName.APPLICANTS, applicants);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
