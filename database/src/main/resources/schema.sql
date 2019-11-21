DROP TABLE IF EXISTS message;

CREATE TABLE message (
  message_id INT NOT NULL AUTO_INCREMENT,
  message_content TEXT NOT NULL,
  message_date DATE NOT NUll,
  PRIMARY KEY (message_id)
);