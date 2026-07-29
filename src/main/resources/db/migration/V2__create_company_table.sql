CREATE TABLE companies(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(250),
    cnpj VARCHAR(250),
    email VARCHAR(250),
    password VARCHAR(250),
    address VARCHAR(500)
);