/*
 * public
 */

DROP SEQUENCE IF EXISTS public.client_sequence;

CREATE SEQUENCE public.client_sequence
	INCREMENT BY 3
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS public.client CASCADE;

CREATE TABLE public.client (
	id int8 NOT NULL,
	"document" varchar(18) NOT NULL,
	document_type varchar(255) NOT NULL,
	email varchar(100) NOT NULL,
	"name" varchar(150) NOT NULL,
	CONSTRAINT client_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.databasechangelog CASCADE;

CREATE TABLE public.databasechangelog (
	id varchar(255) NOT NULL,
	author varchar(255) NOT NULL,
	filename varchar(255) NOT NULL,
	dateexecuted timestamp NOT NULL,
	orderexecuted int4 NOT NULL,
	exectype varchar(10) NOT NULL,
	md5sum varchar(35) NULL,
	description varchar(255) NULL,
	"comments" varchar(255) NULL,
	tag varchar(255) NULL,
	liquibase varchar(20) NULL,
	contexts varchar(255) NULL,
	labels varchar(255) NULL,
	deployment_id varchar(10) NULL
);


DROP TABLE IF EXISTS public.databasechangeloglock CASCADE;

CREATE TABLE public.databasechangeloglock (
	id int4 NOT NULL,
	"locked" bool NOT NULL,
	lockgranted timestamp NULL,
	lockedby varchar(255) NULL,
	CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS public.modality_sequence;

CREATE SEQUENCE public.modality_sequence
	INCREMENT BY 3
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS public.modality CASCADE;

CREATE TABLE public.modality (
	id int8 NOT NULL,
	amortization_method varchar(255) NOT NULL,
	description varchar(150) NOT NULL,
	interest_rate numeric(19,2) NOT NULL,
	"name" varchar(150) NOT NULL,
	rate_period varchar(255) NOT NULL,
	CONSTRAINT modality_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS public.user_sequence;

CREATE SEQUENCE public.user_sequence
	INCREMENT BY 3
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS public."user" CASCADE;

CREATE TABLE public."user" (
	id int8 NOT NULL,
	active bool NOT NULL,
	email varchar(255) NULL,
	last_login timestamp NULL,
	"name" varchar(255) NULL,
	salt bytea NULL,
	"password" varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT email_unique UNIQUE (email),
	CONSTRAINT username_unique UNIQUE (username),
	CONSTRAINT user_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS public.loan_sequence;

CREATE SEQUENCE public.loan_sequence
	INCREMENT BY 3
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS public.loan CASCADE;

CREATE TABLE public.loan (
	id int8 NOT NULL,
	value numeric(19,2) NULL,
	first_payment_date date NOT NULL,
	term_in_months int4 NOT NULL,
	client_id int8 NOT NULL,
	modality_id int8 NOT NULL,
	CONSTRAINT loan_pkey PRIMARY KEY (id),
	CONSTRAINT loan_term_in_months_check CHECK ((term_in_months >= 1)),
	CONSTRAINT fk_loan_client FOREIGN KEY (client_id) REFERENCES client(id),
	CONSTRAINT fk_loan_modality FOREIGN KEY (modality_id) REFERENCES modality(id)
);

DROP SEQUENCE IF EXISTS public.payment_sequence;

CREATE SEQUENCE public.payment_sequence
	INCREMENT BY 3
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS public.payment CASCADE;

CREATE TABLE public.payment (
	id int8 NOT NULL,
	interest numeric(19,2) NULL,
	payment numeric(19,2) NULL,
	payment_date date NOT NULL,
	payment_number int4 NOT NULL,
	principal numeric(19,2) NULL,
	loan_id int8 NOT NULL,
	CONSTRAINT payment_payment_number_check CHECK ((payment_number >= 1)),
	CONSTRAINT payment_pkey PRIMARY KEY (id),
	CONSTRAINT fk_payment_loan FOREIGN KEY (loan_id) REFERENCES loan(id)
);

DROP SEQUENCE IF EXISTS public.profile_sequence;

CREATE SEQUENCE public.profile_sequence
	INCREMENT BY 3
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS public.profile CASCADE;

CREATE TABLE public.profile (
	id int8 NOT NULL,
	profile_code varchar(255) NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT profile_pkey PRIMARY KEY (id),
	CONSTRAINT fk_profile_user FOREIGN KEY (user_id) REFERENCES "user"(id)
);

DROP SEQUENCE IF EXISTS public.refresh_token_sequence;

CREATE SEQUENCE public.refresh_token_sequence
	INCREMENT BY 3
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS public.refresh_token CASCADE;

CREATE TABLE public.refresh_token (
	id int8 NOT NULL,
	"blocked" bool NOT NULL,
	expiration timestamp NULL,
	"token" varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT refresh_token_pkey PRIMARY KEY (id),
	CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES "user"(id)
);

DROP SEQUENCE IF EXISTS public.user_account_key_sequence;

CREATE SEQUENCE public.user_account_key_sequence
	INCREMENT BY 3
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS public.user_account_key CASCADE;

CREATE TABLE public.user_account_key (
	id int8 NOT NULL,
	expiration timestamp NOT NULL,
	"key" varchar(255) NOT NULL,
	"key_type" varchar(255) NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT user_account_key_pkey PRIMARY KEY (id),
	CONSTRAINT fk_user_account_key_user FOREIGN KEY (user_id) REFERENCES "user"(id)
);

DROP SEQUENCE IF EXISTS public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

/*
 * audit
 */

DROP SCHEMA IF EXISTS audit;

CREATE SCHEMA audit AUTHORIZATION postgres;

CREATE TABLE audit.revinfo (
	rev int4 NOT NULL,
	revtstmp int8 NULL,
	CONSTRAINT revinfo_pkey PRIMARY KEY (rev)
);

CREATE TABLE audit.client_aud (
	id int8 NOT NULL,
	rev int4 NOT NULL,
	revtype int2 NULL,
	revend int4 NULL,
	"document" varchar(18) NULL,
	document_type varchar(255) NULL,
	email varchar(100) NULL,
	"name" varchar(150) NULL,
	CONSTRAINT client_aud_pkey PRIMARY KEY (id, rev),
	CONSTRAINT fk_client_aud_revend_revinfo FOREIGN KEY (revend) REFERENCES audit.revinfo(rev),
	CONSTRAINT fk_client_aud_rev_revinfo FOREIGN KEY (rev) REFERENCES audit.revinfo(rev)
);

CREATE TABLE audit.modality_aud (
	id int8 NOT NULL,
	rev int4 NOT NULL,
	revtype int2 NULL,
	revend int4 NULL,
	amortization_method varchar(255) NULL,
	description varchar(150) NULL,
	interest_rate numeric(19,2) NULL,
	"name" varchar(150) NULL,
	rate_period varchar(255) NULL,
	CONSTRAINT modality_aud_pkey PRIMARY KEY (id, rev),
	CONSTRAINT fk_modality_aud_rev_revinfo FOREIGN KEY (rev) REFERENCES audit.revinfo(rev),
	CONSTRAINT fk_modality_aud_revend_revinfo FOREIGN KEY (revend) REFERENCES audit.revinfo(rev)
);