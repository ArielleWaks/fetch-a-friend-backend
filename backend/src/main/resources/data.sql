INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_MODERATOR' );
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_ADMIN');


INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name)
VALUES (1, 0, 1, 'Sitter completed first pet sitting job', 'Congrats on pet sitting for the first time!', 'First Job');

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name)
VALUES (2, 0, 5, 'Sitter completed five pet sitting jobs', 'Congrats on taking care of five pets!', 'High Five!');

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name)
VALUES (3, 0, 10, 'Sitter completed ten pet sitting jobs', 'Congrats on taking care of 10 pets!', 'Experienced Pet Sitter');

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name)
VALUES (4, 0, 25, 'Sitter completed 25 pet sitting jobs', 'Congrats on taking care of 25 pets!', 'Pet Sitter Superstar');

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (31, 3, 3, 'Sitter has taken care of dogs at least 3 times', 'Congrats taking care of dogs!', 'Puppy Pal', 0);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (32, 3, 3, 'Sitter has taken care of cats at least 3 times', 'Congrats taking care of cats!', 'Feline Friend', 1);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (33, 3, 3, 'Sitter has taken care of fish at least 3 times', 'Congrats taking care of fish!', 'Fish Friend', 2);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (34, 3, 3, 'Sitter has taken care of birds at least 3 times', 'Congrats taking care of birds!', 'Bird Buddy', 3);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (34, 3, 3, 'Sitter has taken care of hamsters at least 3 times', 'Congrats taking care of hamsters!', 'Hamster HamSTAR', 4);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (35, 3, 3, 'Sitter has taken care of guinepigs at least 3 times', 'Congrats taking care of guinepigs!', 'Guineapig Pal', 5);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (36, 3, 3, 'Sitter has taken care of rabbits at least 3 times', 'Congrats taking care of rabbits!', 'Bunny Buddy', 6);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (37, 3, 3, 'Sitter has taken care of lizards at least 3 times', 'Congrats taking care of lizards!', 'Lizard Lover', 7);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (38, 3, 3, 'Sitter has taken care of turtles at least 3 times', 'Congrats taking care of turtles!', 'Turtley Awesome', 8);

INSERT IGNORE INTO badge (id, badge_type, number_of_jobs, description, message, name, pet_type)
VALUES (39, 3, 3, 'Sitter has taken care of ferrets at least 3 times', 'Congrats taking care of ferrets!', 'Ferret Friend', 9);