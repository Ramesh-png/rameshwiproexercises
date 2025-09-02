INSERT INTO products (id,name,description,price,quantity) VALUES (1,'Keyboard','Mechanical keyboard',2999,50)
ON DUPLICATE KEY UPDATE name=VALUES(name);
INSERT INTO products (id,name,description,price,quantity) VALUES (2,'Mouse','Wireless mouse',1499,100)
ON DUPLICATE KEY UPDATE name=VALUES(name);
