CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL,
  `product_name` VARCHAR(256) NOT NULL,
  `product_price` DOUBLE NOT NULL,
  PRIMARY KEY (`product_id`));