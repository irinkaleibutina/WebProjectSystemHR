package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.InterviewResult;
import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.InterviewService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;
import by.epam.systemHR.bean.Interview;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        interview.setTechnicalInterview(InterviewResult.valueOf(request.getParameter(ParametersName.STATUS).toUpperCase()));
        interview.setDateTecInt(request.getParameter(ParametersName.DATE));
        interview.setTimeTecInt(request.getParameter(ParametersName.TIME));

        String applicantId = request.getParameter(ParametersName.APPLICANT_ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InterviewService interviewService = serviceFactory.getInterviewService();

        try {
            interviewService.updateTechnicalInterview(applicantId,interview);
            response.sendRedirect(SHOW_PAGE);

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
