package controller.command.impl;

import bean.Applicant;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class AddSkill implements Command {
    private static final Logger logger = LogManager.getLogger(AddSkill.class.getName());

    private static final String SHOW_PAGE = "Controller?command=get_applicant";

    /**
     * Performs a service level call to add skill to applicant
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Applicant current_applicant = (Applicant) request.getSession(true).getAttribute("applicant");

        Map<String, String> skills= new HashMap<>();

        skills.put("Java", request.getParameter("Java"));
        skills.put("C/C++",request.getParameter("C/C++"));
        skills.put(".Net",request.getParameter(".Net"));
        skills.put("C#",request.getParameter("C#"));
        skills.put("Delphi",request.getParameter("Delphi"));
        skills.put("PHP",request.getParameter("PHP"));
        skills.put("Ruby",request.getParameter("Ruby"));
        skills.put("Python",request.getParameter("Python"));
        skills.put("HTML",request.getParameter("HTML"));
        skills.put("PostgreSQL",request.getParameter("PostgreSQL"));
        skills.put("MySQL",request.getParameter("MySQL"));
        skills.put("Microsoft SQL Server",request.getParameter("Microsoft SQL Server"));
        skills.put("Windows",request.getParameter("Windows"));
        skills.put("Unix / Linux / Solaris",request.getParameter("Unix / Linux / Solaris"));
        skills.put("iOS",request.getParameter("iOS"));
        skills.put("BlackBerry",request.getParameter("BlackBerry"));
        skills.put("Android",request.getParameter("Android"));



        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantSkillsService applicantSkillsService = serviceFactory.getApplicantSkillsService();

        try {
            applicantSkillsService.addSkills(current_applicant.getId(), skills);
            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
