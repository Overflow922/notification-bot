CREATE TABLE Schedule_event
(
    id                int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    time_to_trigger   TIMESTAMPTZ,
    notification_text varchar(255) NOT NULL,
    original_rq       varchar(255) NOT NULL,
    created_at        TIMESTAMPTZ
);
select * from Schedule_event;