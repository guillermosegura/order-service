DROP TABLE TE_OrderDetail IF EXISTS;
DROP TABLE TE_Order IF EXISTS;
DROP TABLE TC_Item IF EXISTS;
DROP TABLE TC_Customer IF EXISTS;

CREATE TABLE TC_Customer (
  cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100) PRIMARY KEY,
  nb_firstname varchar(100) NOT NULL,
  nb_lastname varchar(100) NOT NULL,
  nb_secondlastname varchar(100) NOT NULL,
  nb_rfc varchar(13) NOT NULL
);

CREATE TABLE TC_Item (
  cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100) PRIMARY KEY,
  nb_sku varchar(100) NOT NULL,
  tx_description varchar(255) NOT NULL,
  im_price decimal(10,2) NOT NULL
);

CREATE TABLE TE_Order (
  cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100) PRIMARY KEY,
  cd_customer INTEGER NOT NULL,
  dt_order datetime NOT NULL,
  im_subtotal decimal(10,2) NOT NULL,
  im_tax decimal(10,2) NOT NULL,
  im_total decimal(10,2) NOT NULL
);

CREATE TABLE TE_OrderDetail (
  cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100) PRIMARY KEY,
  cd_order INTEGER NOT NULL,
  cd_item INTEGER NOT NULL,
  nu_quantity INTEGER NOT NULL,
  im_price decimal(10,2) NOT NULL,
  im_subtotal decimal(10,2) NOT NULL
);

ALTER TABLE TE_Order
ADD CONSTRAINT FK_TE_Order1
FOREIGN KEY (cd_customer) 
REFERENCES TC_Customer (cd_id);

ALTER TABLE TE_OrderDetail
ADD CONSTRAINT FK_TE_OrderDetail1
FOREIGN KEY (cd_order) 
REFERENCES TE_Order (cd_id);

ALTER TABLE TE_OrderDetail
ADD CONSTRAINT FK_TE_OrderDetail2
FOREIGN KEY (cd_item) 
REFERENCES TC_Item (cd_id);