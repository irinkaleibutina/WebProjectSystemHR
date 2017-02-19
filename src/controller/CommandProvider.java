package controller;

import controller.command.Command;
import controller.command.CommandName;
import controller.command.impl.SignIn;
import controller.command.impl.SignUp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by irinaleibutina on 19.02.17.
 */
public class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<>();

    CommandProvider() {
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());
    }

    Command getCommand(String name) {

        System.out.println("Command getCommand");
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
        // add logic if error
    }
}
