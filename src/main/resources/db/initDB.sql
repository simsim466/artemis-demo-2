DROP TABLE reports IF EXISTS;

CREATE TABLE reports
(
    id    INTEGER IDENTITY PRIMARY KEY,
    text  VARCHAR(255) NOT NULL,
    date  TIMESTAMP default now() NOT NULL,
    queue VARCHAR(50) NOT NULL
);