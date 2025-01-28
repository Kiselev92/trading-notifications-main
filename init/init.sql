-- Для локального запуска необходимо
-- 1. Создать БД в развёрнутом кластере Postgresql
CREATE DATABASE notifications;
-- 2. Создать юзера
CREATE USER notifications WITH PASSWORD 'notifications';
ALTER DATABASE notifications OWNER TO notifications;