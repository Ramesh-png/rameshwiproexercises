INSERT INTO users (id,username,email,password,role,name,address) VALUES
(1,'admin','admin@ecom.com','$2a$10$yqV8I3sWZxI1x2P6W8y0TeO9iJ2cV2v2FqJ7r3r9k7mQk3S7f5W1e','ADMIN','Administrator','Bengaluru')
ON DUPLICATE KEY UPDATE username=VALUES(username);
INSERT INTO users (id,username,email,password,role,name,address) VALUES
(2,'user','user@ecom.com','$2a$10$D2bqVdC2vG6k1vXo0l1VwO3z6o7y0v9f1b2c3d4e5f6g7h8i9j0k.','CUSTOMER','Dharani','Bengaluru')
ON DUPLICATE KEY UPDATE username=VALUES(username);
-- Passwords: Admin@123 / User@123
