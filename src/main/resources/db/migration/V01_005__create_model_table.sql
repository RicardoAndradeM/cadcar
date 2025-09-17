-- MODEL
CREATE TABLE model (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_model_brand FOREIGN KEY (brand_id)
        REFERENCES brand(id)
        ON DELETE CASCADE
);