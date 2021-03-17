# Serveur Artiphonie

![Logo Polytech](https://www.polytech-grenoble.fr/uas/polytech/LOGO/logo-polytech.png)  
**Groupe INFO5A 2020/2021 :**  
*Morgan Crociati*  
*Claire Velut*  
*Rémy Ruzafa*  
*Gaëtan Rival*  

## Informations Actuelles :
![Logo Ubuntu 18.04](https://www.zupimages.net/up/20/14/gvwb.png)
![logo Microsoft Azure](https://www.lac.co.jp/service/img/azure_integration_01.png) 

* Hebergeur : VM Azure Microsoft
* Nom Machine : ArtiphonieServer
* Système Exploitation : Serveur Ubuntu 18.04
* Adresse Publique : **51.124.109.83**
* Port HTTPS : **8443**
* DNS : **artiphonie.westeurope.cloudapp.azure.com**
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

### Installation certificat SSL et configuration :

Deux possibilitées :
- Soit nous créons un certificat auto-signé (Utilisable pour une phase de test)
- Soit nous créons un certificat délivré par une CA (Obtention d'un certificat officiel)

#### 1. Certificat auto-signé
1. Générer un certificat SSL dans un keystore  
    `keytool -genkeypair -alias TOCOMPLETE -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -storepass TOCOMPLETE`  
    Nous avons choisi le format PKCS12 au lieu de JKS pour une meilleure compatibilité avec l'API en Spring. Java ne peut traiter que des certificats en PKCS12.
2. Configurer HTTPS dans Spring Boot
    Dans le fichier 'application.properties' ajouter les lignes suivantes :  
    
        server.port=8443
        server.ssl.key-store-type=PKCS12
        server.ssl.key-store=classpath:keystore.p12
        server.ssl.key-store-password=TOCOMPLETE
        server.ssl.key-alias=TOCOMPLETE
        security.require-ssl=true
  
3. Distribuer le certificats SSL aux clients  
    Transformer notre clé PKCS12 en certificat CRT :  
    `keytool -export -keystore keystore.jks -alias TOCOMPLETE -file myCertificate.crt`
    
    Installer le certificat obtenu sur notre machine client

4. S'assurer que l'accès au site est bien en HTTPS

#### 2. Certificat distribué par une CA
1. Utiliser la CA **Let's Encrypt** afin d'obtenir notre certificat officialisé  
    *Lien utile : https://letsencrypt.org/fr/*
2. Convertir notre jeu de clé obtenu en certificat PCKS12  
`openssl pkcs12 -export -out Cert.p12 -in TOCOMPLETE -inkey TOCOMPLETE -passin pass:TOCOMPLETE -passout pass:TOCOMPLETE`
3. Configurer HTTPS dans Spring Boot
    Dans le fichier 'application.properties' ajouter les lignes suivantes :  
    
        server.port=8443
        server.ssl.key-store-type=PKCS12
        server.ssl.key-store=classpath:Cert.p12
        server.ssl.key-store-password=TOCOMPLETE
        security.require-ssl=true
 
 4. S'assurer que l'accès au site est bien en HTTPS

### Objectifs :
- API (*DONE*)
- BDD (*DONE*)
- HTTPS (*DONE*)
- RGPD (**TO DO**)
- Cryptage BDD (**TO DO**)
- Site Web (**TO DO**)
