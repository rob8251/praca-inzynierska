ALTER TABLE recipes ADD number_of_reviews int;
ALTER TABLE recipes ADD sum_of_reviews int;

CREATE TABLE reviews
(
    id               bigint IDENTITY (1, 1) NOT NULL,
    user_id          bigint,
    comment          varchar(255),
    rating           int,
    created_at       datetime,
    last_modified_at datetime,
    recipe_id        bigint,
    CONSTRAINT pk_reviews PRIMARY KEY (id)
)
GO

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_RECIPE FOREIGN KEY (recipe_id) REFERENCES recipes (id)
GO

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id)
GO

