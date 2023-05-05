package com.iyuriy.notification.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    @Autowired
    public CommandContainer(List<Command> commands) {
        commandMap = commands.stream().collect(toMap(Command::commandType, Function.identity()));
        unknownCommand = new UnknownCommand();
    }

    public Command findCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
