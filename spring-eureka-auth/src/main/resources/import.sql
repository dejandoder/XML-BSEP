INSERT INTO `accomodation_service` VALUES (1,'WiFi'),(2,'Parking'),(3,'Pansion'),(4,'PetFriendly');

INSERT INTO `accomodation_type` VALUES (1,'Hotel'),(2,'Bed&breakfast'),(3,'Motel');

INSERT INTO `user` VALUES (1,'','James','$2a$10$fdm5I8pjSe3wc6tEJldnsuL9XQN0ZJUCppYRM2U7dFmPouBAUMccW','12345678',2,1,'Bond','agent007'),(2,'','User','$2a$10$fdm5I8pjSe3wc6tEJldnsuL9XQN0ZJUCppYRM2U7dFmPouBAUMccW','',0,1,'User','user1'),(3,'','Admin','$2a$10$fdm5I8pjSe3wc6tEJldnsuL9XQN0ZJUCppYRM2U7dFmPouBAUMccW','',1,1,'Admin','admin1');

INSERT INTO `accomodation_unit` VALUES (1,5,5,5,NULL,'Hotel Gacko',1,1,1),(2,5,5,5,NULL,'Hotel Nevesinje',1,1,2);

INSERT INTO `accomodation_unit_services` VALUES (1,1),(1,2),(1,3),(1,4),(2,1);



INSERT INTO `location` VALUES (1,'Lutrija RS, 9, Solunskih Dobrovoljaca, Vrta, Gacko, Gacko municipality, Republika Srpska, 89240, B&H',43.1662,18.5348),(2,'Nemanjića street, Невесиње, Kaluže, Nevesinje, Nevesinje municipality, Republika Srpska, 88280, B&H',43.259,18.1143);


INSERT INTO `price_plan` VALUES (1,'2019-06-01 00:00:00',10,'2019-12-31 00:00:00',1),(2,'2019-06-01 00:00:00',15,'2020-01-24 00:00:00',2);













































INSERT INTO `permission` (`id`,`name`) VALUES (1,'ADD_ACC_UNIT');
INSERT INTO `permission` (`id`,`name`) VALUES (2,'ADD_PRICE_PLAN');
INSERT INTO `permission` (`id`,`name`) VALUES (3,'ADD_IMAGES');
INSERT INTO `permission` (`id`,`name`) VALUES (4,'APPROVE_RESERVATION');
INSERT INTO `permission` (`id`,`name`) VALUES (5,'CONFIRM_RESERVATION');
INSERT INTO `permission` (`id`,`name`) VALUES (6,'DECLINE_RESERVATION');
INSERT INTO `permission` (`id`,`name`) VALUES (7,'DO_AGENT_RESERVATION');

INSERT INTO `permission` (`id`,`name`) VALUES (8,'ADD_ACC_SERVICE');
INSERT INTO `permission` (`id`,`name`) VALUES (9,'REMOVE_ACC_SERVICE');
INSERT INTO `permission` (`id`,`name`) VALUES (10,'ADD_ACC_TYPE');
INSERT INTO `permission` (`id`,`name`) VALUES (11,'REMOVE_ACC_TYPE');

INSERT INTO `permission` (`id`,`name`) VALUES (12,'ADD_AGENT');
INSERT INTO `permission` (`id`,`name`) VALUES (13,'ACTIVATE_USER');
INSERT INTO `permission` (`id`,`name`) VALUES (14,'BLOCK_USER');
INSERT INTO `permission` (`id`,`name`) VALUES (15,'APPROVE_RECENSION');
INSERT INTO `permission` (`id`,`name`) VALUES (16,'DECLINE_RECENSION');


INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (1,1);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (1,2);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (1,3);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (1,4);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (1,5);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (1,6);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (1,7);

INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,8);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,9);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,10);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,11);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,12);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,13);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,14);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,15);
INSERT INTO `user_permissions` (`user_id`,`permissions_id`) VALUES (3,16);

