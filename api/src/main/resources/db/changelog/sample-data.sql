-- user admin

INSERT INTO public."user"
(id, active, email, last_login, "name", salt, "password", username)
VALUES(nextval('public.user_sequence'), true, 'gordon.freeman@blackmesa.com', NULL, 'GORDON FREEMAN', decode('E9709A050F0CA5C69723307DABFF2D02','hex'), '41b2ec666b6ca0fedbe05ecd8bd53f50', 'gordon.freeman');

INSERT INTO public.profile
(id, profile_code, user_id)
VALUES(nextval('public.profile_sequence'), 'ADMIN', 1);

INSERT INTO public.user_account_key
(id, expiration, "key", "key_type", user_id)
VALUES(nextval('public.user_account_key_sequence'), '2020-09-13 12:16:26.657', 'e367dbeb-bb8a-46ed-af23-dfce42a499b2', 'ACTIVATION_KEY', 1);

INSERT INTO public.refresh_token
(id, "blocked", expiration, "token", user_id)
VALUES(nextval('public.refresh_token_sequence'), false, (select current_timestamp + interval '30 day'), 'e9386aac-5ce4-4b86-8d48-9d064dcb7912', 1);

-- client

INSERT INTO public.client
(id, "document", document_type, email, "name")
VALUES(nextval('public.client_sequence'), '22322803065', 'CPF', 'alyx@email.com', 'Alyx Vance');

INSERT INTO public.client
(id, "document", document_type, email, "name")
VALUES(nextval('public.client_sequence'), '40469675000107', 'CNPJ', 'contact@aperturelab.com', 'Aperture Laboratories');

-- modality

INSERT INTO public.modality
(id, amortization_method, description, interest_rate, "name", rate_period)
VALUES(nextval('modality_sequence'), 'SAC', 'Personal Credit SAC', 0.72, 'Personal Credit SAC', 'MONTHLY');

INSERT INTO public.modality
(id, amortization_method, description, interest_rate, "name", rate_period)
VALUES(nextval('modality_sequence'), 'PRICE', 'Personal Credit Price', 9.00, 'Personal Credit Price', 'YEARLY');

-- loan

INSERT INTO public.loan
(id, value, first_payment_date, term_in_months, client_id, modality_id)
VALUES(nextval('loan_sequence'), 1000.00, '2020-10-12', 4, 1, 1);

INSERT INTO public.payment
(id, interest, payment, payment_date, payment_number, principal, loan_id)
VALUES(nextval('payment_sequence'), 7.20, 257.20, '2020-10-12', 1, 250.00, 1);

INSERT INTO public.payment
(id, interest, payment, payment_date, payment_number, principal, loan_id)
VALUES(nextval('payment_sequence'), 5.40, 255.40, '2020-11-12', 2, 250.00, 1);

INSERT INTO public.payment
(id, interest, payment, payment_date, payment_number, principal, loan_id)
VALUES(nextval('payment_sequence'), 3.60, 253.60, '2020-12-12', 3, 250.00, 1);

INSERT INTO public.payment
(id, interest, payment, payment_date, payment_number, principal, loan_id)
VALUES(nextval('payment_sequence'), 1.80, 251.80, '2021-01-12', 4, 250.00, 1);



