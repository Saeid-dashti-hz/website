-- V1__initial_setup.sql

-- Drop the existing tables if they exist
DROP TABLE IF EXISTS patient_problems;
DROP TABLE IF EXISTS patients;

-- Create the patients table with the columns in the desired order
CREATE TABLE patients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    family VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    insurance_type VARCHAR(50) NOT NULL,
    additional_info TEXT,
    active BOOLEAN NOT NULL DEFAULT true
);

-- Create the patient_problems table
CREATE TABLE patient_problems (
    patient_id INTEGER NOT NULL,
    problem VARCHAR(255) NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(id)
);
