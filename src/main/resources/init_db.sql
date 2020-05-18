CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `users`
(
    `user_id`  bigint       NOT NULL AUTO_INCREMENT,
    `name`     varchar(256) NOT NULL,
    `login`    varchar(256) NOT NULL,
    `password` varchar(256) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `roles`
(
    `role_id`   bigint       NOT NULL AUTO_INCREMENT,
    `role_name` varchar(256) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `users_roles`
(
    `role_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    KEY `users_roles_roles_fk_idx` (`role_id`),
    KEY `users_roles_users_fk_idx` (`user_id`),
    CONSTRAINT `users_roles_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `users_roles_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders`
(
    `order_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id`  bigint NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY `orders_user_fk_idx` (`user_id`),
    CONSTRAINT `orders_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `internet_shop`.`shopping_carts`
(
    `cart_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`cart_id`),
    INDEX `casts_user_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `carts_user_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`products`
(
    `product_id` BIGINT(11)   NOT NULL,
    `name`       VARCHAR(256) NOT NULL,
    `price`      DOUBLE       NOT NULL,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `orders_products`
(
    `order_id`   bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `order_product_order_fk_idx` (`order_id`),
    KEY `order_product_product_fk_idx` (`product_id`),
    CONSTRAINT `orders_products_order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `orders_products_product_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `internet_shop`.`shopping_carts_products`
(
    `cart_id`    BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    INDEX `carts_products_carts_fk_idx` (`cart_id` ASC) VISIBLE,
    INDEX `carts_products_products_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `carts_products_carts_fk`
        FOREIGN KEY (`cart_id`)
            REFERENCES `internet_shop`.`shopping_carts` (`cart_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `carts_products_products_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
