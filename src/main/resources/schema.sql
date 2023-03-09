-- Table PRICES --
DROP TABLE IF EXISTS PRICES CASCADE;
CREATE TABLE PRICES(
    ID INTEGER AUTO_INCREMENT,
    BRAND_ID INTEGER NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    PRICE_LIST INTEGER NOT NULL,
    PRODUCT_ID INTEGER NOT NULL,
    PRIORITY INTEGER NOT NULL,
    PRICE DECIMAL(10, 2) NOT NULL,
    CURR VARCHAR(3) NOT NULL,

    PRIMARY KEY(ID),
    FOREIGN KEY(BRAND_ID) REFERENCES BRAND(ID)
);

-- Table BRAND --
DROP TABLE IF EXISTS BRANDS CASCADE;
CREATE TABLE BRANDS(
    ID INTEGER AUTO_INCREMENT,
    BRAND VARCHAR(255) NOT NULL,

    PRIMARY KEY(ID)
);