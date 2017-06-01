package controller.command.impl;

import bean.Interview;
import bean.InterviewResult;
import controller.command.Command;
import controller.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.InterviewService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class AddTechnicalInterview implements Command {
    private static final Logger logger = LogManager.getLogger(AddTechnicalInterview.class.getName());

    private static final String SHOW_PAGE = "Controller?command=show_applicants";

    /**
     * Performs a service level call to add technical interview
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {

        Interview interview = new Interview();
        interview.setTechnicalInterview(InterviewResult.valueOf(request.getParameter(STATUS).toUpperCase()));
        interview.setDateTecInt(request.getParameter(DATE));
        interview.setTimeTecInt(request.getParameter(TIME));

        String applicantId = request.getParameter(APPLICANT_ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InterviewService interviewService = serviceFactory.getInterviewService();

        try {
            interviewService.updateTechnicalInterview(applicantId,interview);
            response.sendRedirect(SHOW_PAGE);

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
