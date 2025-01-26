--
--INSERT INTO reservationschema.users ( address, email, password, phone_number, role, sex, surname)
--VALUES
-- ( '123 Rue Principale, Lomé', 'jean.dupont@example.com', 'motdepasse1', '+22890123456', 'CLIENT', 'M', 'Dupont');
-- 
--INSERT INTO reservationschema.users ( address, email, password, phone_number, role, sex, surname)
--VALUES
--  ( '456 Avenue de la République, Lomé', 'marie.durand@example.com', 'motdepasse2', '+22890765432', 'CLIENT', 'F', 'Durand');

--INSERT INTO reservationschema.service ( capacity, current_occupancy, image, is_available, name, price, type)
--VALUES
--  ( 2, 0, 'image1.jpg', true, 'Chambre Standard', 50000, 'standard'),
--  ( 4, 0, 'image2.jpg', true, 'Suite Familiale', 120000, 'suite');


--
--INSERT INTO reservationschema.reservation ( amount, number_of_users, payement_date, payement_method, status, service_id, user_id)
--VALUES
--  ( 50000, 1, '2025-01-18', 'carte bancaire', 'confirmée', 1, 1),
--  ( 120000, 2, '2025-01-19', 'paypal', 'en attente', 2, 2);

--
--INSERT INTO  reservationschema.lignereservation ( price, quantity, service_id)
--VALUES
--  ( 50000, 1, 1),
--  ( 60000, 2, 2);
