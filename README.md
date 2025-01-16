## Configuration et microservices

### Étapes initiales
On commence par :
- Le **Config Server**
- **Eureka**
- **Zuul**
- Les microservices **Commandes** et **Produits** sont configurés après le reste.

  ### Repartition des taches
  -Manal Kerroumi:
     +Euureka / microservice de produit / Microservice de Commande
  -Inssaf Bellamkadem :
     +Zuul / Client UI / Config Server 
  

### Difficultés rencontrées
- Compatibilité des versions, surtout avec **Zuul**.
- Problèmes liés au `pom.xml` et à l'exécution.
- La communication entre les microservices

### POM et configuration
- Base de données utilisée : **H2**.

### Points clés
- **URL de test avec Zuul** :
  - [http://localhost:9004/microservice-commandes/test-config](http://localhost:9004/microservice-commandes/test-config)

- **URL pour vérifier le Config Server** :
  - [http://localhost:9101/microservice-commandes/default/master](http://localhost:9101/microservice-commandes/default/master)

- **Ports** :
  - Microservice **Produits** : `9001`.

### Zuul
Le **Zuul** permet :
- La communication entre microservices en utilisant un **proxy**.
- La centralisation du client pour unifier les entrées HTTP via un port unique (`9004`).

### Eureka
- **Eureka** gère les microservices, effectue le **load balancing**, et permet aux microservices de s'enregistrer.
- Fonctionne en coordination avec **Zuul** (gateway).

### Config Server
- Récupère la configuration déposée dans le dépôt **Git** pour l'implémenter dans le microservice local.

### Hystrix
- Bibliothèque développée par **Netflix**.
- Objectif : Gérer la **tolérance aux pannes** dans les systèmes distribués complexes.

### Feign
Feign est utilisé comme client HTTP simplifié pour appeler d'autres microservices.





