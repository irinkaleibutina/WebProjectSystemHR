package by.epam.systemHR.controller;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.command.CommandName;
import by.epam.systemHR.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling commands and command types
 */
public class CommandProvider {

    private static final Logger logger = LogManager.getLogger(CommandProvider.class.getName());
    private Map<CommandName, Command> commands = new HashMap<>();

    CommandProvider() {
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        commands.put(CommandName.REGISTRATION, new Registration());
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.SIGN_OUT, new SignOut());
        commands.put(CommandName.MAIN_PAGE, new MainPage());
        commands.put(CommandName.SHOW_VACANCIES, new ShowVacancies());
        commands.put(CommandName.SHOW_APPLICANTS, new ShowApplicants());
        commands.put(CommandName.DELETE_VACANCY, new DeleteVacancy());
        commands.put(CommandName.REDIRECT, new Redirect());
        commands.put(CommandName.SUBMIT_APPLICATION, new SubmitApplication());
        commands.put(CommandName.SEARCH_APPLICANT, new SearchApplicant());
        commands.put(CommandName.ADD_VACANCY, new AddVacancy());
        commands.put(CommandName.SHOW_VACANCY, new ShowVacancy());
        commands.put(CommandName.ADD_ADMIN, new AddAdmin());
        commands.put(CommandName.SHOW_ADMINS, new ShowAdmins());
        commands.put(CommandName.ADD_PRE_INTERVIEW, new AddPreliminaryInterview());
        commands.put(CommandName.ADD_TEC_INTERVIEW, new AddTechnicalInterview());
        commands.put(CommandName.UPDATE_APPLICANT, new UpdateApplicant());
        commands.put(CommandName.SEARCH_VACANCY, new SearchVacancy());
        commands.put(CommandName.UPDATE_ADMIN, new UpdateAdmin());
        commands.put(CommandName.GET_APPLICANT, new GetApplicantInfo());
        commands.put(CommandName.DELETE_APPLICATION, new DeleteApplication());
        commands.put(CommandName.ADD_NEWS, new AddNews());
        commands.put(CommandName.TAKE_TECHNOLOGIES, new TakeTechnologies());
        commands.put(CommandName.ADD_SKILL, new AddSkill());
        commands.put(CommandName.TAKE_NEWS, new TakeNews());
        commands.put(CommandName.SEARCH_NEWS, new SearchNews());
        commands.put(CommandName.DELETE_NEWS, new DeleteNews());
        commands.put(CommandName.GET_NEWS, new GetNews());
        commands.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    /**
     * Method which return instance of {@link Command} from request param
     *
     * @param name
     *            a string that contains command name
     * @return instance of {@link Command}
     */
    Command getCommand(String name) {
        if (name == null) {
            return commands.get(CommandName.WRONG_REQUEST);
        } else {
            try {
                CommandName commandName = CommandName.valueOf(name.toUpperCase());
                return commands.get(commandName);
            } catch (IllegalArgumentException | NullPointerException e) {
               logger.error(e);
                return commands.get(CommandName.WRONG_REQUEST);
            }
        }
    }
}
