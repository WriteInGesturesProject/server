# Serveur Artiphonie

**Groupe INFO5A 2020/2021 :**  
*Morgan Crociati*  
*Claire Velut*  
*Rémy Ruzafa*  
*Gaëtan Rival*  

## Informations Actuelles :
* Hebergeur : VM Azure Microsoft
* Nom Machine : ArtiphonieServer
* Système Exploitation : Serveur Ubuntu 18.04
* Adresse Publique : **51.124.109.83**
* Port HTTPS : **8443**
* DNS : Pas défini
* Taille : Standard B1s
* Processeur : 1 (Virtuel)
* RAM : 1 Gio

## Mise en place du serveur :  

Dans notre cas, nous avons choisi comme herbergeur *Microsoft Azure* et comme système d'exploitation pour le serveur *Ubuntu version 18.04*

### Installation Serveur PostgreSQL et configuration :
1. Installer le serveur à l'aide de la commande suivante :

    `sudo apt install postgresql`
2. Puis créer une table de donnée avec les commande suivantes :

    `sudo -i -u postgres #Se connecter à la base`  
    `createdb <NomTable> #Dans notre cas la table se nomme "Projet"`
    
3. Optionnel : SI vous devez supprimer la table vous devez tout d'abord vérifier qu'aucune session n'est ouverte, si c'est le cas faite :  

    SELECT pg_terminate_backend(pg_stat_activity.pid)
    FROM pg_stat_activity  
    WHERE pg_stat_activity.datname = 'NOM BDD' -- ← Change avec votre BDD  
    AND pid <> pg_backend_pid();
    
    Puis une fois fait, supprimer la base avec la commande ci-jointe :  
    
    `dropdb <NomTable>`
    
### Installation API et configuration :
1. Cloner le dépot git sur le serveur :
  `git clone https://github.com/WriteInGesturesProject/server.git`
2. Ensuite vérifier les informations dans le fichier **application.properties** en complétant les champs manquants
3. Assurez vous que votre serveur possède les mêmes ports configurés qu'avec l'API avec aucune restriction dessus (dans notre cas nous avons dû configurer un port 8080 pour les tests HTTP et un port 8443 pour les requetes HTTPS)
4. Puis éxecuter le script Maven avec la commande :
  	`./mwvn`
    
    **Informations complémentaires :**  
    Il est possible qu'il vous manque le JDK compatible avec l'application, pour cela nous avons codé l'API sous JAVA 11 donc installer le **JDK 11** sur le serveur à l'aide du lien suivant : https://doc.ubuntu-fr.org/openjdk
