--====================================================================================
-- СОЗДАНИЕ ПОСЛЕДОВАТЕЛЬНОСТИ ДЛЯ ШАБЛОНА
--====================================================================================
DROP SEQUENCE IF EXISTS SEQ_TEMPLATE;
CREATE SEQUENCE IF NOT EXISTS SEQ_TEMPLATE START WITH 1 INCREMENT BY 1;

--====================================================================================
-- ТАБЛИЦА ПОДДЕРЖИВАЕМЫХ ТИПОВ
--====================================================================================
DROP TABLE IF EXISTS T_TYPE;
CREATE TABLE IF NOT EXISTS T_TYPE (
  ID       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  NAME     VARCHAR(100) NOT NULL,
  ACTIVE   BOOLEAN      NOT NULL             DEFAULT TRUE,
  USERNAME VARCHAR(100) NOT NULL,
  ADD_DT   TIMESTAMP    NOT NULL             DEFAULT NOW()
);

--====================================================================================
-- ТАБЛИЦА С ХРАНЕНИЕМ ШАБЛОНОВ
--====================================================================================
DROP TABLE IF EXISTS T_TEMPLATE;
CREATE TABLE IF NOT EXISTS T_TEMPLATE (
  ID         INT          NOT NULL PRIMARY KEY,
  NAME       VARCHAR(256) NOT NULL,
  ACTIVE     BOOLEAN               DEFAULT TRUE,
  TABLE_NAME VARCHAR(50)  NOT NULL,
  USERNAME   VARCHAR(100) NOT NULL,
  ADD_DT     TIMESTAMP    NOT NULL DEFAULT NOW()
);

DROP INDEX IF EXISTS INDEX_TEMPLATE;
CREATE UNIQUE INDEX INDEX_TEMPLATE
  ON T_TEMPLATE (ID, TABLE_NAME);

--====================================================================================
-- ТАБЛИЦА С МЕТАИНФОРМАЦИЕЙ ПО ШАБЛОНАМ
--====================================================================================
DROP TABLE IF EXISTS T_TEMPLATE_META_INFO;
CREATE TABLE IF NOT EXISTS T_TEMPLATE_META_INFO (
  ID          INT          NOT NULL             AUTO_INCREMENT PRIMARY KEY,
  ID_TEMPLATE INT          NOT NULL,
  NAME        VARCHAR(512) NOT NULL,
  ACTIVE      BOOLEAN                           DEFAULT TRUE,
  COLUMN_NAME VARCHAR(50)  NOT NULL,
  ID_TYPE     INT          NOT NULL,
  LENGTH      INT          NOT NULL             DEFAULT 1,
  ORDERR      VARCHAR(2)   NOT NULL,
  USERNAME    VARCHAR(100) NOT NULL,
  ADD_DT      TIMESTAMP    NOT NULL             DEFAULT NOW(),
  FOREIGN KEY (ID_TEMPLATE) REFERENCES T_TEMPLATE (ID)
  ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (ID_TYPE) REFERENCES T_TYPE (ID)
  ON DELETE CASCADE ON UPDATE CASCADE,
);

DROP INDEX IF EXISTS INDEX_TEMPLATE_META_INFO;
CREATE UNIQUE INDEX INDEX_TEMPLATE_META_INFO
  ON T_TEMPLATE_META_INFO (ID_TEMPLATE, COLUMN_NAME);

--====================================================================================
-- НАПОЛНЕНИЕ ТЕСТОВЫМИ ДАННЫМИ
--====================================================================================
INSERT INTO T_TEMPLATE (
  ID,
  TABLE_NAME,
  NAME,
  USERNAME
) VALUES (
  (SELECT NEXT VALUE FOR SEQ_TEMPLATE),
  '1',
  'Тестовый шаблон',
  'ezhov_da'
);

INSERT INTO T_TYPE (
  NAME,
  USERNAME
) VALUES
  ('STRING', 'ezhov_da'),
  ('INTEGER', 'ezhov_da');

INSERT INTO T_TEMPLATE_META_INFO (
  ID_TEMPLATE,
  COLUMN_NAME,
  NAME,
  ID_TYPE,
  LENGTH,
  ORDERR,
  USERNAME
) VALUES
  ((SELECT MAX(ID)
    FROM T_TEMPLATE), '1', 'Код', 1, 500, '00', 'ezhov_da'),
  ((SELECT MAX(ID)
    FROM T_TEMPLATE), '2', 'Название', 1, 500, '01', 'ezhov_da'),
  ((SELECT MAX(ID)
    FROM T_TEMPLATE), '3', 'Количество', 2, 500, '02', 'ezhov_da')




