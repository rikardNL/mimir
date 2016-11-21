CREATE TABLE "Suggestion" (
    "id" UUID NOT NULL PRIMARY KEY,
    "user_id" VARCHAR NOT NULL,
    "entity_id" VARCHAR NOT NULL,
    "new_value" BYTEA,
    "operation" VARCHAR,
    "status" VARCHAR,
    "timestamp" TIMESTAMP
);

CREATE TABLE "Claim" (
    "id" UUID NOT NULL PRIMARY KEY,
    "user_id" VARCHAR NOT NULL,
    "entity_id" VARCHAR NOT NULL,
    "status" VARCHAR,
    "timestamp" TIMESTAMP
);
