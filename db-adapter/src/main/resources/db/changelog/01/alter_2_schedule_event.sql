ALTER TABLE Schedule_event
    ALTER COLUMN id TYPE bigint,
    ALTER COLUMN user_id TYPE bigint USING user_id::bigint,
    ADD FOREIGN KEY (user_id) REFERENCES Users(id);