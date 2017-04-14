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

/**
 * Created by irinaleibutina on 4/10/17.
 */
public class SearchApplicant implements Command {
    private static final Logger logger = LogManager.getLogger(SearchApplicant.class.getName());

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String ATTR_APPLICANT = "applicant";
    private static final String ATTR_ERROR = "error";
    private static final String SHOW_PAGE = "/WEB-INF/jsp/searchApplicant.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();

        Applicant applicant = new Applicant();
        try {
            applicant = applicantService.searchApplicant(name, surname);
            System.out.println(applicant);
            request.setAttribute(ATTR_APPLICANT, applicant);
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
