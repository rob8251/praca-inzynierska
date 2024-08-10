CREATE TABLE categories
(
    id   bigint IDENTITY (1, 1) NOT NULL,
    name varchar(255),
    CONSTRAINT pk_categories PRIMARY KEY (id)
)
GO

CREATE TABLE difficulty_level
(
    id               bigint IDENTITY (1, 1) NOT NULL,
    level            int                    NOT NULL,
    name             varchar(255),
    description      varchar(255),
    created_at       datetime,
    last_modified_at datetime,
    CONSTRAINT pk_difficultylevel PRIMARY KEY (id)
)
GO

CREATE TABLE ingredients
(
    id               bigint IDENTITY (1, 1) NOT NULL,
    name             varchar(255)           NOT NULL,
    created_at       datetime,
    last_modified_at datetime,
    CONSTRAINT pk_ingredients PRIMARY KEY (id)
)
GO

CREATE TABLE measurements
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    abbreviation varchar(255)           NOT NULL,
    name         varchar(255)           NOT NULL,
    CONSTRAINT pk_measurements PRIMARY KEY (id)
)
GO

CREATE TABLE recipes
(
    id                  bigint IDENTITY (1, 1) NOT NULL,
    name                varchar(255),
    description         varchar(255),
    prep_time_minutes   varchar(255),
    servings            int,
    image_url           varchar(255),
    category_id         bigint,
    difficulty_level_id bigint,
    user_id             bigint,
    created_at          datetime,
    last_modified_at    datetime,
    CONSTRAINT pk_recipes PRIMARY KEY (id)
)
GO

ALTER TABLE recipes
    ADD CONSTRAINT FK_RECIPES_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id)
GO

ALTER TABLE recipes
    ADD CONSTRAINT FK_RECIPES_ON_DIFFICULTY_LEVEL FOREIGN KEY (difficulty_level_id) REFERENCES difficulty_level (id)
GO

ALTER TABLE recipes
    ADD CONSTRAINT FK_RECIPES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id)
GO

CREATE TABLE recipe_ingredients
(
    amount         float(53),
    measurement_id bigint,
    recipe_id      bigint NOT NULL,
    ingredient_id  bigint NOT NULL,
    CONSTRAINT pk_recipe_ingredients PRIMARY KEY (recipe_id, ingredient_id)
)
GO

ALTER TABLE recipe_ingredients
    ADD CONSTRAINT FK_RECIPE_INGREDIENTS_ON_INGREDIENT FOREIGN KEY (ingredient_id) REFERENCES ingredients (id)
GO

ALTER TABLE recipe_ingredients
    ADD CONSTRAINT FK_RECIPE_INGREDIENTS_ON_MEASUREMENT FOREIGN KEY (measurement_id) REFERENCES measurements (id)
GO

ALTER TABLE recipe_ingredients
    ADD CONSTRAINT FK_RECIPE_INGREDIENTS_ON_RECIPE FOREIGN KEY (recipe_id) REFERENCES recipes (id)
GO

CREATE TABLE steps
(
    id          bigint IDENTITY (1, 1) NOT NULL,
    step_number int                    NOT NULL,
    description varchar(255)           NOT NULL,
    recipe_id   bigint,
    CONSTRAINT pk_steps PRIMARY KEY (id)
)
GO

ALTER TABLE steps
    ADD CONSTRAINT FK_STEPS_ON_RECIPE FOREIGN KEY (recipe_id) REFERENCES recipes (id)
GO

