-- Table: public.employee

-- DROP TABLE public.employee;

CREATE TABLE public.employee
(
  employee_id bigint NOT NULL,
  first_name character varying(255) COLLATE pg_catalog."default",
  last_name character varying(255) COLLATE pg_catalog."default",
  phone_number character varying(255) COLLATE pg_catalog."default",
  CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
)
  WITH (
  OIDS = FALSE
       )
  TABLESPACE pg_default;

ALTER TABLE public.employee
  OWNER to saginijoy;--nss_admin