# notification-bot

Open source implementation of server that sends notifications based on some schedule. Schedule is configurable per user.
Currently, we are considering Telegram bot api as a main destination of generated notifications. But API is flexible
enough to add another destinations.

## Schedule:

To add schedule you need send text message (**command**) to bot.

### Command structure:

`<command>` `<trigger time>` `<triger date>` `<notification text>`. Where:<br/>
<br/>
`<command>`:<br/>
/add - adds new schedule
<br/>
<br/>
`<trigger time>` - time when bot will send notification.</br><br/>
`<triger date>` - day when user wants to get notification. Optional value. If not present, bot will send notification
only once (during the same day when user added notification). Possible values:<br/>
every day</br>
Monday, Tuesday, Wednesday, Thursday, Friday</br><br/>
`<notification text>` - text that will be printed by bot in notification message.

### Command examples

`/add 11:15 check email`
`/add 16:30 every day call boss`
`/add 10:00 every Monday`

We do not support multi-language support at this stage.

## Structural diagrams

![Structural diagram](img/c4-scructural-diagram.png)