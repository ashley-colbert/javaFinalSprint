CREATE TABLE public.doctor
(
    license_number character varying(64) NOT NULL,
    specialization character varying(64),
    doctor_id integer,
    PRIMARY KEY (license_number)
    
);

ALTER TABLE doctor
ADD CONSTRAINT FKey1
FOREIGN KEY (doctor_id) REFERENCES users(id);
