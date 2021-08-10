INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');

INSERT INTO mesto (id, grad, drzava) VALUES (1, 'Novi Sad', 'SRB');
INSERT INTO mesto (id, grad, drzava) VALUES (2, 'Budapest', 'HUN');
INSERT INTO mesto (id, grad, drzava) VALUES (3, 'Antwerpen', 'BEL');
INSERT INTO mesto (id, grad, drzava) VALUES (4, 'Timisoara', 'ROU');
INSERT INTO mesto (id, grad, drzava) VALUES (5, 'Pilton', 'ENG');
INSERT INTO mesto (id, grad, drzava) VALUES (6, 'Chicago', 'USA');

INSERT INTO festival (id, naziv, pocetak, kraj, cena_karte, broj_karata, mesto_id)
			VALUES (1, 'Exit', '2021-07-07', '2021-07-10', 4000, 40000, 1);

INSERT INTO festival (id, naziv, pocetak, kraj, cena_karte, broj_karata, mesto_id)
			VALUES (2, 'Sziget', '2021-08-10', '2021-08-17', 3000, 40000, 2);
			
INSERT INTO festival (id, naziv, pocetak, kraj, cena_karte, broj_karata, mesto_id)
			VALUES (3, 'Tomorrowland', '2021-07-19', '2021-07-28', 6500, 80000, 3);

INSERT INTO festival (id, naziv, pocetak, kraj, cena_karte, broj_karata, mesto_id)
			VALUES (4, 'Revolution', '2021-08-08', '2021-08-10', 5000, 20000, 4);

INSERT INTO festival (id, naziv, pocetak, kraj, cena_karte, broj_karata, mesto_id)
			VALUES (5, 'Glastonbury', '2021-06-26', '2021-06-30', 5000, 20000, 5);

INSERT INTO festival (id, naziv, pocetak, kraj, cena_karte, broj_karata, mesto_id)
			VALUES (6, 'Lollapalooza', '2021-07-29', '2021-08-01', 10000, 100000, 6);
              
