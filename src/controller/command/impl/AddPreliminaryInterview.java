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

/**
 * Created by irinaleibutina on 4/12/17.
 */
public class AddPreliminaryInterview implements Command {
    private static final Logger logger = LogManager.getLogger(AddPreliminaryInterview.class.getName());

    private static final String STATUS = "status";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String APPLICANT_ID = "id";
    private static final String INDEX_PAGE = "/";
    private static final String ATTR_ERROR = "error";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Interview interview = new Interview();
        interview.setPreliminaryInterview(InterviewResult.valueOf(request.getParameter(STATUS).toUpperCase()));
        interview.setDatePreInt(request.getParameter(DATE));
        interview.setTimePreInt(request.getParameter(TIME));
        int applicantId = Integer.parseInt(request.getParameter(APPLICANT_ID));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InterviewService interviewService = serviceFactory.getInterviewService();

        try {
            interviewService.updatePreliminaryInterview(applicantId,interview);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
        response.sendRedirect(request.getContextPath() + INDEX_PAGE);
    }
}
