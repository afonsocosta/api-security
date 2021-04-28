--liquibase formatted sql logicalFilePath:db.changelog-1.0

--changeset afonso.oliveira:db.changelog-1.0

CREATE TABLE oauth.user(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `password` VARCHAR(250) NOT NULL,
    `cellphone` INT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE oauth.role(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth`.`user_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  CONSTRAINT `fk_user_role_user`
    FOREIGN KEY (`id`)
    REFERENCES `oauth`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fklk_user_role_role`
    FOREIGN KEY (`id`)
    REFERENCES `oauth`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

create table oauth.oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth.oauth_client_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth.oauth_access_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(256)
);

create table oauth.oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table oauth.oauth_code (
  code VARCHAR(256), authentication LONG VARBINARY
);

create table oauth.oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP NULL,
	lastModifiedAt TIMESTAMP NULL
);


--rollback DROP TABLE oauth.user;
--rollback DROP TABLE oauth.role;
--rollback DROP TABLE oauth.user_role;
--rollback DROP TABLE oauth.oauth_client_details;
--rollback DROP TABLE oauth.oauth_client_token;
--rollback DROP TABLE oauth.oauth_access_token;
--rollback DROP TABLE oauth.oauth_refresh_token;
--rollback DROP TABLE oauth.oauth_code;
--rollback DROP TABLE oauth.oauth_approvals;
