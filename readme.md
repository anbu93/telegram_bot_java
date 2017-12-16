# Java telegram bot framework

For simple make telegram bot on java.

Used command pattern [GoF]

All commands are context free.

Session is user context. Can be included session variables.

**Warning**: don't use session for store necessary variables.
If bot restarted, all session data are deleted (not saving in database or file).

Simple using bot see in `com.vova_cons.telegram_bot_framework.test` package.

Author contact: anbu23477@gmail.com