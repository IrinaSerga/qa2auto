-- Цель: автоматизировать процесс добавления API-тестов(ограниченный набор шагов) в бд (делегировать командам manualQA)
-- Добавление API тестов, сценарий:
-- вход под пользователем
-- выбор продукта (например, Узбекистан, Молдова, Испания)
-- добавление/редактирование подсистемы
--  ключевых полей (наименование, тестовая группа, приоритет)
-- добавление/редактирование теста
--  ключевых полей (имя, состояние, приоритет, описание)
-- добавление/редактирование действий по тесту
-- выбор из справочника
-- ввод в поле текста DML/DDL

--Было бы интересно реализовать валидатор DML/DDL скриптов в дальнейшем
-- DDL - ранится ли без ошибок на указанной тестовой бд
-- DML - есть список требований(на будущее: эталонноые данные можно получать прямо здесь, запуская сначала  DDL,
-- затем DML(на указанной тестовой бд) и получать результат


-- продукт, для которого добавляются ТК
-- айди(PK), код(U), имя
CREATE TABLE product
(
    id SERIAL NOT NULL PRIMARY KEY,
    code CHAR(3) UNIQUE,
    name VARCHAR(50) NOT NULL
);

-- Примеры продуктов для тестирования
INSERT INTO product (code, name)
VALUES ('USA', 'United States Platform'),
       ('GER', 'German Insurance Portal'),
       ('ESP', 'Spanish Mobile App'),
       ('FRA', 'French Web CRM');


-- подсистемы
-- имя подсистемы, уникально в рамках продукта
-- айди(PK),
-- name (уникальность в рамках подсистемы и продукта)-- UNIQUE (product_id, name)
-- приоритет запуска, группа тестирования
-- тестовая группа

CREATE TABLE subsystem
(
    id SERIAL NOT NULL PRIMARY KEY,
    product_id INT REFERENCES product(id),
    name VARCHAR(100) NOT NULL,
    priority_id SMALLINT REFERENCES subsystem_priority(id) CHECK (priority_id >= 0 AND priority_id <= 9) NOT NULL,
    test_group_id INT REFERENCES test_group(id),
    UNIQUE (product_id, name)

);


-- вспомогательная, справочник приоритетов
-- айди и нейм
-- '1-important; 2-middle; 3-low; 4-only release; 5-only manual run'
CREATE TABLE subsystem_priority
(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- Приоритеты подсистем (только текстовое имя)
INSERT INTO subsystem_priority (name)
VALUES ('important'),
       ('middle'),
       ('low'),
       ('only release'),
       ('only manual run');


-- вспомогательная, справочник групп тестирования
-- айди и нейм
-- группы manualQA(b_qa, c_qa, d_qa) + autoQA(auto_qa)
CREATE TABLE test_group
(
    id SMALLSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- Вариант с короткими названиями
INSERT INTO test_group (name)
VALUES ('b_qa'),
       ('c_qa'),
       ('d_qa'),
       ('auto_qa');


INSERT INTO subsystem (product_id, name, priority_id, test_group_id)
VALUES (1, 'Authentication Service', 1, 4),
       (1, 'User Profile Management', 2, 1),
       (1, 'Notification Center', 3, 2),
       (2, 'Policy Calculation Engine', 1, 3),
       (2, 'Claims Workflow', 2, 4),
       (2, 'Reporting Module', 4, 1),
       (3, 'Mobile Push Handler', 2, 2),
       (3, 'Geo-location Tracking', 3, 3),
       (4, 'Client CRM Sync', 1, 4),
       (4, 'Release Notes Manager', 5, 1);



-- основная сущность - тест test_case
-- айди, предполагаю максимальное кол-во записей, потому BIGSERIAL
-- subsystem_id - привязка к подсистеме
-- name - имя теста
-- state_id - состояние теста (активный, неактивный)
-- priority_id - приоритеты теста (1,2,3)

CREATE TABLE test_case
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    subsystem_id INT REFERENCES subsystem(id),
    name VARCHAR(100) NOT NULL,
    state_id SMALLINT REFERENCES test_case_state(id) CHECK (state_id >= 0 AND state_id <= 5),
    priority_id SMALLINT REFERENCES test_case_priority(id) CHECK (priority_id >= 0 AND priority_id <= 3),
    description VARCHAR(100)

);

INSERT INTO test_case (subsystem_id, name, state_id, priority_id, description)
VALUES (1, 'Verify login with valid credentials', 1, 1, 'Basic login test'),
       (1, 'Check error on invalid password', 1, 2, 'Negative case for login'),
       (2, 'Submit insurance claim with PDF', 2, 2, 'Claim form upload'),
       (2, 'Cancel claim before approval', 3, 3, 'User cancels pending claim'),
       (3, 'Send push notification to device', 1, 1, 'Push via Firebase'),
       (3, 'Track device location with GPS off', 4, 2, 'Negative tracking test'),
       (4, 'Generate client contact report', 1, 1, 'Scheduled report'),
       (4, 'Edit client contact manually', 5, 2, 'Manual update flow'),
       (1, 'Login throttling after 5 failures', 4, 3, 'Security feature test'),
       (2, 'Reopen deprecated claim', 5, 1, 'Edge case for claim reuse');




-- test_case_priority - приоритет запуска тестов
-- айди и нейм
CREATE TABLE test_case_priority
(
    id SMALLSERIAL NOT NULL PRIMARY KEY CHECK (id >= 1 AND id <= 3),
    name VARCHAR(10) UNIQUE NOT NULL
);

INSERT INTO test_case_priority (name)
VALUES ('high'),
       ('medium'),
       ('low');


-- test_case_state -- состояние тестов (new. active. inactive)
CREATE TABLE test_case_state
(
    id SMALLSERIAL NOT NULL PRIMARY KEY CHECK (id >= 1 AND id <= 9),
    name VARCHAR(10) UNIQUE NOT NULL
);

INSERT INTO test_case_state (name)
VALUES ('active'),
       ('inactive'),
       ('draft'),
       ('deprecated'),
       ('ready');



-- Шаблон действия action_directory
-- выполнить DDL, выполнить DML, сравнить результат с эталонным
CREATE TABLE action_directory
(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    description VARCHAR(200)

);

INSERT INTO action_directory (name, description)
VALUES ('run_ddl', 'Выполнить DDL-запрос, например CREATE, ALTER, DROP'),
       ('run_dml', 'Выполнить DML-запрос, например INSERT, UPDATE, DELETE'),
       ('assert_result', 'Сравнить результат выполнения с эталонным значением');


-- действия в рамках кейса
CREATE TABLE test_case_action
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    test_case_id INT REFERENCES test_case(id),
    action_id INT REFERENCES action_directory(id),
    step_order INT
);
-- Тест 1: создаёт таблицу, затем проверяет её наличие
INSERT INTO test_case_action (test_case_id, action_id, step_order)
VALUES (1, 1, 1), -- run_ddl
       (1, 3, 2);
-- assert_result

-- Тест 2: добавляет запись и проверяет результат
INSERT INTO test_case_action (test_case_id, action_id, step_order)
VALUES (2, 2, 1), -- run_dml
       (2, 3, 2);
-- assert_result

-- Тест 3: просто проверка (без DDL/DML)
INSERT INTO test_case_action (test_case_id, action_id, step_order)
VALUES (3, 3, 1);
-- assert_result


-- параметры, которые вводятся для этого действия
-- param_value - здесь текст DDL/текст DML/эталонное значение
CREATE TABLE test_case_action_param
(
    id                  SERIAL PRIMARY KEY,
    test_case_action_id INT NOT NULL REFERENCES test_case_action (id) ON DELETE CASCADE,
    param_value         TEXT
);


-- Для test_case_id = 1
INSERT INTO test_case_action_param (test_case_action_id, param_value)
VALUES (1, 'CREATE TABLE test_users (id INT PRIMARY KEY, name VARCHAR(100));'),
       (2, 'SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ''test_users'';'),
       (2, '1');

-- Для test_case_id = 2
INSERT INTO test_case_action_param (test_case_action_id, param_value)
VALUES (3, 'INSERT INTO test_users (id, name) VALUES (1, ''Alice'');'),
       (4, 'SELECT name FROM test_users WHERE id = 1;'),
       (4, 'Alice');

-- Для test_case_id = 3
INSERT INTO test_case_action_param (test_case_action_id, param_value)
VALUES (5, 'SELECT COUNT(*) FROM test_users;'),
       (5, '1');


--- распределение CRUD
--- ролевое распределение: добавление(CREATE)/удаление(DELETE)/редактирование(UPDATE) тестов в подсистеме доступно только если subsystem.test_group_id
--- равно группе пользователя
--- просмотр доступен всем (READ)
--- только тестовая группа auto_qa имеет полный доступ к CRUD


CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(100) UNIQUE NOT NULL,
    username VARCHAR(50),
    group_id INTEGER REFERENCES test_group (id)
);




-- справочник действий (CRUD)
CREATE TABLE permission
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL -- значения: 'CREATE', 'READ', 'UPDATE', 'DELETE'
);

--какие группы имеют какие права

CREATE TABLE test_group_permission
(
    test_group_id INTEGER REFERENCES test_group (id),
    permission_id INTEGER REFERENCES permission (id),
    PRIMARY KEY (test_group_id, permission_id)
);



--- состояние теста test_case_state active/inactive может устанавливать только пользователь группы auto_qa












