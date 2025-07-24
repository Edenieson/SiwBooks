ALTER TABLE libro ALTER COLUMN id SET DEFAULT nextval('libro_seq');
ALTER TABLE autore ALTER COLUMN id SET DEFAULT nextval('autore_seq');


INSERT INTO autore(nome, cognome, data_nascita, data_morte, nazionalita, portrait)VALUES('Stephen', 'King', '1988-05-05', null, 'United States', null);

INSERT INTO utente(id, nome, cognome, email) VALUES(99, 'admin', 'admin', 'admin@admin.com');
INSERT INTO credenziali(id, username, password, role, utente_id) VALUES(99, 'admin' ,'$2a$12$Y808TDBHbA61wLtRazVPbewaPzfLFrK2AY.2wXcYMYO/cjUy3/jSe', 'ADMIN', 99);