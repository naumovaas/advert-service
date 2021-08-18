CREATE TABLE USERS (
  id SERIAL PRIMARY KEY,
  username varchar(30) NOT NULL,
  password varchar(128) NOT NULL,
  first_name varchar(30),
  last_name varchar(30),
  rating real
);

CREATE TABLE USERS_ROLE (
  user_id int NOT NULL,
  role varchar(20) NOT NULL,
  
  CONSTRAINT FK_USERS_ROLE_USER_ID
    FOREIGN KEY (user_id)
      REFERENCES USERS(id)
);

CREATE TABLE CATEGORY (
  id SERIAL PRIMARY KEY,
  parent_category_id int,
  title varchar(50),
  
  CONSTRAINT FK_CATEGORY_PARENT_CATEGORY_ID
    FOREIGN KEY (parent_category_id)
      REFERENCES CATEGORY(id)
);

CREATE TABLE ADVERT (
  id SERIAL PRIMARY KEY,
  user_id int NOT NULL,
  category_id int NOT NULL,
  text varchar(2000),
  status varchar(20),
  date timestamptz,
  priority_flag boolean,
  
  CONSTRAINT FK_ADVERT_USERS_ID
    FOREIGN KEY (user_id)
      REFERENCES USERS(id),
  CONSTRAINT FK_ADVERT_CATEGORY_ID
    FOREIGN KEY (category_id)
      REFERENCES CATEGORY(id)
);

CREATE TABLE COMMENT (
  id SERIAL PRIMARY KEY,
  user_id int NOT NULL,
  advert_id int NOT NULL,
  text varchar(500),
  date timestamptz,
  
  CONSTRAINT FK_COMMENT_ADVERT_ID
    FOREIGN KEY (advert_id)
      REFERENCES ADVERT(id),
  CONSTRAINT FK_COMMENT_USERS_ID
    FOREIGN KEY (user_id)
      REFERENCES USERS(id)
);

CREATE TABLE DIALOG (
  id SERIAL PRIMARY KEY,
  buyer_user_id int NOT NULL,
  seller_user_id int NOT NULL,

  CONSTRAINT FK_DIALOG_BUYER_ID
    FOREIGN KEY (buyer_user_id)
      REFERENCES USERS(id),
  CONSTRAINT FK_DIALOG_SELLER_ID
    FOREIGN KEY (seller_user_id)
      REFERENCES USERS(id)
);

CREATE TABLE MESSAGE (
  id SERIAL PRIMARY KEY,
  dialog_id int NOT NULL,
  text varchar(500),
  date timestamptz,

  CONSTRAINT FK_MESSAGE_DIALOG_ID
    FOREIGN KEY (dialog_id)
      REFERENCES DIALOG(id)
);