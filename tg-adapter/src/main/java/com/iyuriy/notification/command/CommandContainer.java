package com.iyuriy.notification.command;

import com.google.common.collect.ImmutableMap;
import com.iyuriy.notification.repositories.UserRepository;
import com.iyuriy.notification.services.RestEventSender;
import org.springframework.stereotype.Service;

import static com.iyuriy.notification.common.parser.UserEventType.*;

@Service
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(RestEventSender restEventSender, UserRepository userRepository
    ) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getUserEventType(), new StartCommand(userRepository))
                .put(STOP.getUserEventType(), new StopCommand())
                .put(HELP.getUserEventType(), new HelpCommand())
                .put(NO.getUserEventType(), new NoCommand())
                .put(ADD.getUserEventType(), new AddCommand())
                .put(TIME_ZONE.getUserEventType(), new TimeZoneCommand(userRepository))
                .build();
        unknownCommand = new UnknownCommand();
    }

    public Command findCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
