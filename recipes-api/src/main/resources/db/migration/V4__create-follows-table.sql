CREATE TABLE follows
(
    follower_id bigint NOT NULL,
    followed_id bigint NOT NULL,
    CONSTRAINT pk_follows PRIMARY KEY (follower_id, followed_id)
)
GO

ALTER TABLE follows
    ADD CONSTRAINT FK_FOLLOWS_ON_FOLLOWED FOREIGN KEY (followed_id) REFERENCES users (id)
GO

ALTER TABLE follows
    ADD CONSTRAINT FK_FOLLOWS_ON_FOLLOWER FOREIGN KEY (follower_id) REFERENCES users (id)
GO