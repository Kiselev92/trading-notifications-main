Для локального запуска:
1. Установить Docker Desktop, запустить
2. Запустиь файл init/docker-compose.yml
3. Выполнить запросы из init/init.sql
4. Запустить метод main (Должно упасть)
5. Сверху справа раскрыть конфигурации (выбран запуск Spring Boot), edit configurations -> more options -> environment variables
   Дописать значения в строку (без пробелов) DB_HOST=localhost;DB_PORT=5432;DB_PASSWORD=notifications;DB_USER=notifications;DB_NAME=notifications