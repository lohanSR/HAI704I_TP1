# HAI704I - TP1 Java RMI

Application de cabinet vétérinaire en Java RMI avec un serveur et plusieurs clients.

## Structure

Le projet est séparé en trois dossiers :
- `common` : interfaces et classes partagées entre le client et le serveur
- `server` : implémentation des objets distants et lancement du serveur
- `client` : interface en ligne de commande et logique côté client

## Compilation

Depuis la racine du projet :

```bash
mkdir -p out/common out/server out/client

javac -d out/common common/src/vet/*.java
javac -cp out/common -d out/server server/src/vet/*.java
javac -cp out/common -d out/client client/src/vet/*.java
```

## Lancement

### Serveur

```bash
java -cp out/common:out/server vet.Server
```

Le serveur crée le registre RMI sur le port `1099` et publie le cabinet sous le nom `Cabinet123`.

### Client

Dans un autre terminal :

```bash
java -cp out/common:out/client vet.Client localhost 1099
```

Il est possible de lancer plusieurs clients en même temps.

Si aucun argument n'est donné, le client utilise `localhost` et le port `1099`.

## Fonctionnalités

Depuis la CLI, il est possible de :

- lister les patients ;
- rechercher un patient ;
- enregistrer un nouveau patient ;
- consulter et modifier son dossier de suivi ;
- ajouter une observation ;
- s'abonner ou se désabonner des alertes ;
- quitter proprement l'application.
