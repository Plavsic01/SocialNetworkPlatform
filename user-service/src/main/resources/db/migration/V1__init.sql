CREATE TABLE users
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    username            VARCHAR(255) NOT NULL,
    password            VARCHAR(20)  NOT NULL,
    first_name          VARCHAR(255) NOT NULL,
    last_name           VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL,
    profile_picture_url VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

CREATE TABLE roles
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);