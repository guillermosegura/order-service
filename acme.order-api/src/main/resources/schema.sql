DROP TABLE TBL_order IF EXISTS;

CREATE TABLE TBL_order (
  cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100) PRIMARY KEY,
  nb_city varchar(50) NOT NULL,
  nb_phone varchar(50) NOT NULL,
  nb_addressLine1 varchar(50) NOT NULL,
  nb_addressLine2 varchar(50) DEFAULT NULL,
  nb_state varchar(50) DEFAULT NULL,
  nb_country varchar(50) NOT NULL,
  nb_postalCode varchar(15) NOT NULL,
  nb_territory varchar(10) NOT NULL
);
