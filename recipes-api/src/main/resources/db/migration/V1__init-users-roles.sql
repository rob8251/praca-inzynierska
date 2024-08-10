CREATE TABLE roles
(
    id   bigint IDENTITY (1, 1) NOT NULL,
    name varchar(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
)
    GO

CREATE TABLE users
(
    id               bigint IDENTITY (1, 1) NOT NULL,
    username         varchar(255) NOT NULL,
    password         varchar(255) NOT NULL,
    first_name       varchar(255) NOT NULL,
    last_name        varchar(255) NOT NULL,
    email            varchar(255) NOT NULL,
    created_at       datetime,
    last_modified_at datetime,
    CONSTRAINT pk_users PRIMARY KEY (id)
)
    GO

CREATE TABLE users_roles
(
    role_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
)
    GO

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email)
    GO

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username)
    GO

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id)
    GO

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id)
    GO