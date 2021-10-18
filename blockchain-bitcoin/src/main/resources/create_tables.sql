CREATE TABLE IF NOT EXISTS `database_blockchain`.`bitcoin_blocks` (
  `id` BIGINT(100) UNSIGNED NOT NULL AUTO_INCREMENT,
  `hash` VARCHAR(100) NULL,
  `previous_hash` VARCHAR(100) NULL,
  `data` VARCHAR(100) NULL,
  `timestamp` BIGINT(100) NULL,
  `nonce` BIGINT(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `hash_UNIQUE` (`hash` ASC) VISIBLE,
  UNIQUE INDEX `previous_hash_UNIQUE` (`previous_hash` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `database_blockchain`.`bitcoin_transactions` (
  `id` BIGINT(100) UNSIGNED NOT NULL AUTO_INCREMENT,
  `hash` VARCHAR(100) NULL,
  `hash_block` VARCHAR(150) NULL,
  `address_sender` VARCHAR(108) NULL,
  `address_recipient` VARCHAR(108) NULL,
  `data` VARCHAR(100) NULL,
  `timestamp` BIGINT(100) NULL,
  `amount` DECIMAL(18,8) NULL,
  `fees` DECIMAL(18,8) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `hash_UNIQUE` (`hash` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `database_blockchain`.`bitcoin_wallets` (
  `id` BIGINT(100) UNSIGNED NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(108) NULL,
  `code` VARCHAR(3) NULL,
  `amount` DECIMAL(18,8) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `address_UNIQUE` (`address` ASC) VISIBLE);