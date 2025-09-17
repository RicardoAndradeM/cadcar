-- VEHICLE
CREATE TABLE vehicle (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    model_id BIGINT NOT NULL,
    number_plate VARCHAR(7) UNIQUE NOT NULL,
    chassis_number VARCHAR(17) UNIQUE NOT NULL,
    model_year INT NOT NULL,
    factory_year INT NOT NULL,
    color VARCHAR(50),
    CONSTRAINT fk_vehicle_client FOREIGN KEY (client_id)
        REFERENCES client(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_vehicle_model FOREIGN KEY (model_id)
        REFERENCES model(id)
        ON DELETE CASCADE
);