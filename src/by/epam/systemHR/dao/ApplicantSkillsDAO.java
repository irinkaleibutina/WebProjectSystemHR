package by.epam.systemHR.dao;

import by.epam.systemHR.bean.Skill;
import by.epam.systemHR.dao.exception.DAOException;
import java.util.List;
import java.util.Map;

/**
 * Interface {@code ApplicantSkillsDAO} is the class, that contains methods
 * to work with applicant skills info in the data base.
 * @author irinaleibutina
 */
public interface ApplicantSkillsDAO {

    void addSkills(int applicantId, Map<String, String> skills) throws DAOException;
    void deleteSkills(int applicantId, int skillId) throws DAOException;
    List<Skill> getAllTechnologies() throws DAOException;
}
