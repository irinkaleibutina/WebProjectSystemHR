package controller.command.impl;

import bean.Applicant;
import controller.command.Command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantService;
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
public class ShowApplicants implements Command {
    private static final Logger logger = LogManager.getLogger(ShowApplicants.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/applicants.jsp";
    private static final String ATTR_FAIL = "fail_take_appl";

    /**
     * Performs a service level call to get all applicants
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            ApplicantService service = factory.getApplicantService();
            List<Applicant> applicants = service.getApplicants();

            if (applicants.isEmpty()) {
                request.setAttribute(ATTR_FAIL, "Applicants not found");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute(APPLICANTS, applicants);
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
