package by.epam.systemHR.service.impl;

import by.epam.systemHR.bean.Skill;
import by.epam.systemHR.dao.ApplicantSkillsDAO;
import by.epam.systemHR.dao.exception.DAOException;
import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.service.ApplicantSkillsService;
import by.epam.systemHR.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class implements {@link src.dao.ApplicantSkillsService}
 */
public class ApplicantSkillsServiceImpl implements ApplicantSkillsService {

    /**
     * Method  performs a dao level call to add skills info
     * to a particular applicant
     *
     * @param applicantId
     * @param skills      all applicant's skills
     * @throws ServiceException
     */
    @Override
    public void addSkills(int applicantId, Map<String, String> skills) throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantSkillsDAO applicantSkillsDAO = daoFactory.getApplicantSkillsDAO();
        try {
            applicantSkillsDAO.addSkills(applicantId, skills);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method  performs a dao level call to get all technologies
     *
     * @return list of instances of {@link Skill}
     * @throws ServiceException
     */
    @Override
    public List<Skill> getAllTechnologies() throws ServiceException {

        List<Skill> skills = new ArrayList<>();
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantSkillsDAO applicantSkillsDAO = daoFactory.getApplicantSkillsDAO();
        try {
            skills = applicantSkillsDAO.getAllTechnologies();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return skills;
    }
}
