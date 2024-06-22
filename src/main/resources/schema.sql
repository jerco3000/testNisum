CREATE TABLE "USER" (
                        id UUID PRIMARY KEY,
                        name VARCHAR(100),
                        email VARCHAR(100) UNIQUE,
                        password VARCHAR(100),
                        created TIMESTAMP,
                        modified TIMESTAMP,
                        last_login TIMESTAMP,
                        token VARCHAR(255),
                        is_active BOOLEAN
);

CREATE TABLE "PHONE" (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         number VARCHAR(20),
                         citycode VARCHAR(10),
                         contrycode VARCHAR(10),
                         user_id UUID,
                         FOREIGN KEY (user_id) REFERENCES "USER"(id)
);


