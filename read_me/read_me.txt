Pour tester l'api seulement

pour tester l'affichage de l'historique de reservation il faut utiliser:
http://localhost:8093/users/1/history

Pour tester l'affichage des services il faut:
http://localhost:8093/facilities

Pour effectuer une reservation

http://localhost:8093/recherche/enregistrementreservation

voici un exemple de donnees a inserer
{
  "amount": "100.0",
  "payement_date": "2025-01-08T12:00:00",
  "payement_method": "Credit Card",
  "status": "Annulé",
  "user": {
    "id": 1
  },
  "service": {
    "id": 2
  },
  "notification": {
    "id": 10
  }
}

Pour creer un nouveau service , Voici ce qu'il faut taper
http://localhost:8093/facilities/reservations

Voici un exemple de donees a inserer
{
    "name":"ama",
    "type":"afi",
    "availability":"oui",
    "price":"788"
}

Pour tester l'affichage de service  par disponibilite il faut:
http://localhost:8093/recherche/services/search?&isAvailable=true

Pour tester l'affichage de service  par non disponibilité il faut:
http://localhost:8093/recherche/services/search?&type=&isAvailable=false


Pour tester l'affichage de service  par nom et  disponibilité il faut:
http://localhost:8093/recherche/services/search?&isAvailable=true&name=ama


Pour tester l'affichage de service  par nom et non disponibilité il faut:
http://localhost:8093/recherche/services/search?&isAvailable=false&name=ama

Pour tester l'affichage de service  par type et disponibilité il faut:
http://localhost:8093/recherche/services/search?&isAvailable=true&type=Restauration
