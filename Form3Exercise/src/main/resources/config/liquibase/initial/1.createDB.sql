--liquibase formatted sql

--changeset form3Exercise:1

CREATE TABLE IF NOT EXISTS T_PARTY (
  id INT(11) NOT NULL AUTO_INCREMENT,
  account_name VARCHAR(100) NULL, 
  account_number VARCHAR(40) NOT NULL,
  account_number_code VARCHAR(40) NULL,
  account_type INT(11) NULL,     
  address VARCHAR(250) NULL,
  bank_id INT(11) NOT NULL,
  bank_id_code VARCHAR(40) NOT NULL,
  name VARCHAR(250),  
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS T_ATTRIBUTE_FX (
  id INT(11) NOT NULL AUTO_INCREMENT,
  contract_reference VARCHAR(50) NOT NULL, 
  exchange_rate DECIMAL(13, 4) NOT NULL,
  original_amount DECIMAL(13, 4) NOT NULL,
  original_currency VARCHAR(10) NOT NULL, 
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS T_CHARGES_INFORMATION (
  id INT(11) NOT NULL AUTO_INCREMENT,
  bearer_code VARCHAR(50) NOT NULL, 
  receiver_charges_amount DECIMAL(13, 4) NOT NULL,
  receiver_charges_currency VARCHAR(10) NOT NULL, 
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS T_CHARGES (
  id INT(11) NOT NULL AUTO_INCREMENT,
  amount DECIMAL(13, 4) NOT NULL,
  currency VARCHAR(10) NOT NULL, 
  charges_info_id INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT `fk_t_charges_t_charges_information`
    FOREIGN KEY (charges_info_id)
    REFERENCES T_CHARGES_INFORMATION (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)  
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS T_PAYMENT_RESOURCE (
  id VARCHAR(40) NOT NULL,
  type VARCHAR(40) NOT NULL,
  version INT(11) NOT NULL,
  organisation_id VARCHAR(40) NOT NULL,
  amount DECIMAL(13, 4) NOT NULL,
  currency VARCHAR(10) NOT NULL,
  end_to_end_reference VARCHAR(250) NOT NULL,
  numeric_reference INT(11) NOT NULL,
  payment_id BIGINT NOT NULL,
  payment_purpose VARCHAR(250) NOT NULL,
  payment_scheme VARCHAR(50) NOT NULL,
  payment_type VARCHAR(50) NOT NULL,
  processing_date DATE NOT NULL,
  reference VARCHAR(250) NOT NULL,
  scheme_payment_sub_type VARCHAR(100) NOT NULL,
  scheme_payment_type VARCHAR(100) NOT NULL, 
  beneficiary_party_id INT(11) NOT NULL,
  debtor_party_id INT(11) NOT NULL,
  sponsor_party_id INT(11) NOT NULL, 
  fx_id INT(11) NOT NULL,
  charges_info_id INT(11) NOT NULL, 
  PRIMARY KEY (id),
  CONSTRAINT `fk_t_payment_resource_t_beneficiary_party`
    FOREIGN KEY (beneficiary_party_id)
    REFERENCES T_PARTY (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_payment_resource_t_debtor_party`
    FOREIGN KEY (debtor_party_id)
    REFERENCES T_PARTY (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_payment_resource_t_sponsor_party`
    FOREIGN KEY (sponsor_party_id)
    REFERENCES T_PARTY (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_payment_resource_t_fx`
    FOREIGN KEY (fx_id)
    REFERENCES T_ATTRIBUTE_FX (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_payment_resource_charges_info`
    FOREIGN KEY (charges_info_id)
    REFERENCES T_CHARGES_INFORMATION (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
