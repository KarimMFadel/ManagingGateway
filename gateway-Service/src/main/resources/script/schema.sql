
DROP TABLE if exists DEVICES CASCADE; 
DROP TABLE if exists GATEWAYS CASCADE;  

CREATE TABLE GATEWAYS (
   ID  BIGSERIAL NOT NULL,
   UNIQUE_SERIAL_NUMBER VARCHAR(50),
   NAME VARCHAR(255),
   IP_ADDRESS VARCHAR(255),
   PRIMARY KEY (ID),
   UNIQUE KEY SERIAL_NUMBER_UNIQUE (UNIQUE_SERIAL_NUMBER)
);


CREATE TABLE DEVICES (
   ID  BIGSERIAL NOT NULL,
   UNIQUE_NUMBER BIGINT,
   VENDOR VARCHAR(200),
   CREATED_ON DATETIME,
   STATUS VARCHAR(50),
   GATEWAYS_ID BIGINT,
   PRIMARY KEY (ID),
   UNIQUE KEY UNIQUE_NUMBER_UNIQUE (UNIQUE_NUMBER),
   CONSTRAINT FK_GATEWAY_DEVICE FOREIGN KEY (GATEWAYS_ID) REFERENCES GATEWAYS (ID),
);
