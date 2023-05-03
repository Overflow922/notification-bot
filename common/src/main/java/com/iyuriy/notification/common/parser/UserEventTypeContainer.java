//package com.iyuriy.notification.common.parser;
//
//import java.util.List;
//import java.util.Map;
//
//public class UserEventTypeContainer {
//
//    private final Map<String, UserEventType> commandMap;
//    private final UserEventType unknownCommand;
//
//    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService,
//                            JavaRushGroupClient javaRushGroupClient, GroupSubService groupSubService,
//                            StatisticsService statisticsService) {
//
//
//
//        commandMap = Map.<String, UserEventType>builder()
//                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
//                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
//                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
//                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
//                .build();
//
//        unknownCommand = new UnknownCommand(sendBotMessageService);
//    }
//
//
//    public UserEventTypeContainer(Map<String, UserEventType> commandMap, UserEventType unknownCommand) {
//        this.commandMap = commandMap;
//        this.unknownCommand = unknownCommand;
//    }
//}
//
