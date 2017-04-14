package controller.command.impl;

import bean.Applicant;
import controller.command.Command;
import controller.command.exception.CommandException;

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

/**
 * Created by irinaleibutina on 29.03.17.
 */
public class ShowApplicants implements Command {
    private static final Logger logger = LogManager.getLogger(ShowApplicants.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/applicants.jsp";
    private static final String ATTR_APPLICANTS = "applicants_list";
    private static final String ATTR_ERROR = "error";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            ApplicantService service = factory.getApplicantService();
            List<Applicant> applicants = service.getApplicants();
            request.setAttribute(ATTR_APPLICANTS, applicants);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
        requestDispatcher.forward(request, response);
    }
}
