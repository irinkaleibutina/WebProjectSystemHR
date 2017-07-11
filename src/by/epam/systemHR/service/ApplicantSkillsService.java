package by.epam.systemHR.service;

import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.bean.Skill;

import java.util.List;
import java.util.Map;

/**
 * Interface {@code ApplicantSkillsService} is the class, that contains methods
 * to work with applicant skills info
 * @author irinaleibutina
 */
public interface ApplicantSkillsService {

    void addSkills(int applicantId, Map<String, String> skills) throws ServiceException;
    List<Skill> getAllTechnologies() throws ServiceException;
}
