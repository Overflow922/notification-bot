CREATE TABLE Schedule_event
(
    id                 bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    time_to_trigger    TIMESTAMPTZ,
    notification_text  varchar(255) NOT NULL,
    original_rq        varchar(255) NOT NULL,
    is_sent_to_adapter TIMESTAMPTZ,
    created_at         TIMESTAMPTZ,
    user_id            bigint
);