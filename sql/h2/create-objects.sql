--====================================================================================
-- СОЗДАНИЕ ПОСЛЕДОВАТЕЛЬНОСТИ ДЛЯ ШАБЛОНА
--====================================================================================
DROP SEQUENCE IF EXISTS SEQ_DOCUMENT;
CREATE SEQUENCE IF NOT EXISTS SEQ_DOCUMENT START WITH 1 INCREMENT BY 1;

--====================================================================================
-- ТАБЛИЦА ПОДДЕРЖИВАЕМЫХ ТИПОВ
--====================================================================================
DROP TABLE IF EXISTS T_FIELD_TYPE;
CREATE TABLE IF NOT EXISTS T_FIELD_TYPE (
  ID       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  NAME     VARCHAR(100) NOT NULL,
  ACTIVE   BOOLEAN      NOT NULL             DEFAULT TRUE,
  USERNAME VARCHAR(100) NOT NULL,
  ADD_DT   TIMESTAMP    NOT NULL             DEFAULT NOW()
);

--====================================================================================
-- ТАБЛИЦА С ХРАНЕНИЕМ ШАБЛОНОВ
--====================================================================================
DROP TABLE IF EXISTS T_DOCUMENT;
CREATE TABLE IF NOT EXISTS T_DOCUMENT (
  ID         INT          NOT NULL PRIMARY KEY,
  NAME       VARCHAR(256) NOT NULL,
  ACTIVE     BOOLEAN               DEFAULT TRUE,
  TABLE_NAME VARCHAR(50)  NOT NULL,
  USERNAME   VARCHAR(100) NOT NULL,
  ADD_DT     TIMESTAMP    NOT NULL DEFAULT NOW()
);

DROP INDEX IF EXISTS INDEX_DOCUMENT;
CREATE UNIQUE INDEX INDEX_DOCUMENT
  ON T_DOCUMENT (ID, TABLE_NAME);

--====================================================================================
-- ТАБЛИЦА С МЕТАИНФОРМАЦИЕЙ ПО ШАБЛОНАМ
--====================================================================================
DROP TABLE IF EXISTS T_DOCUMENT_FIELD;
CREATE TABLE IF NOT EXISTS T_DOCUMENT_FIELD (
  ID          INT          NOT NULL             AUTO_INCREMENT PRIMARY KEY,
  ID_DOCUMENT INT          NOT NULL,
  NAME        VARCHAR(512) NOT NULL,
  ACTIVE      BOOLEAN                           DEFAULT TRUE,
  COLUMN_NAME VARCHAR(50)  NOT NULL,
  ID_TYPE     INT          NOT NULL,
  LENGTH      INT          NOT NULL             DEFAULT 1,
  ORDERR      VARCHAR(2)   NOT NULL,
  USERNAME    VARCHAR(100) NOT NULL,
  ADD_DT      TIMESTAMP    NOT NULL             DEFAULT NOW(),
  FOREIGN KEY (ID_DOCUMENT) REFERENCES T_DOCUMENT (ID)
  ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (ID_TYPE) REFERENCES T_FIELD_TYPE (ID)
  ON DELETE CASCADE ON UPDATE CASCADE,
);

DROP INDEX IF EXISTS INDEX_DOCUMENT_FIELD;
CREATE UNIQUE INDEX INDEX_DOCUMENT_FIELD
  ON T_DOCUMENT_FIELD (ID_DOCUMENT, COLUMN_NAME);

--====================================================================================
-- НАПОЛНЕНИЕ ТЕСТОВЫМИ ДАННЫМИ
--====================================================================================
INSERT INTO T_DOCUMENT (
  ID,
  TABLE_NAME,
  NAME,
  USERNAME
) VALUES (
  (SELECT NEXT VALUE FOR SEQ_DOCUMENT),
  '1',
  'Тестовый шаблон',
  'ezhov_da'
);

INSERT INTO T_FIELD_TYPE (
  NAME,
  USERNAME
) VALUES
  ('STRING', 'ezhov_da'),
  ('INTEGER', 'ezhov_da');

INSERT INTO T_DOCUMENT_FIELD (
  ID_DOCUMENT,
  COLUMN_NAME,
  NAME,
  ID_TYPE,
  LENGTH,
  ORDERR,
  USERNAME
) VALUES
  ((SELECT MAX(ID)
    FROM T_DOCUMENT), '1', 'Код', 1, 500, '00', 'ezhov_da'),
  ((SELECT MAX(ID)
    FROM T_DOCUMENT), '2', 'Название', 1, 500, '01', 'ezhov_da'),
  ((SELECT MAX(ID)
    FROM T_DOCUMENT), '3', 'Количество', 2, 500, '02', 'ezhov_da')




