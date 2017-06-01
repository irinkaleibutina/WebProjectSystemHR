package controller.command.impl;

import bean.Skill;
import controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantSkillsService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class TakeTechnologies implements Command {
    private static final Logger logger = LogManager.getLogger(TakeTechnologies.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/technologies.jsp";
    private static final String ATTR_FAIL = "fail_tech";

    /**
     * Performs a service level call to get all technologies
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Skill> skills = new ArrayList<>();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantSkillsService applicantSkillsService = serviceFactory.getApplicantSkillsService();

        try {
            skills = applicantSkillsService.getAllTechnologies();
            request.setAttribute(TECHNOLOGY, skills);

            if(skills.isEmpty()){
                request.setAttribute(ATTR_FAIL, SKILLS);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
                requestDispatcher.forward(request, response);
            }
            else {
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
