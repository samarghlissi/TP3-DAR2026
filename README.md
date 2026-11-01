# TP3-DAR2026 : Serveur multi-threads (sockets TCP)

**Auteur :** ghlissi samar, LSI 3
**Matière :** Développement d'applications réparties, 2026/2027



## Activité 3-1 : acceptation de plusieurs clients
Le serveur (`MultiThreadServer`) boucle sur `accept()`. Pour chaque client il incrémente un numéro d'ordre, affiche son IP (`getRemoteSocketAddress()`) et lance un thread `ClientProcess` qui envoie au client son numéro d'ordre.

Lancement : `MultiThreadServer`, puis `Client` (ou `telnet localhost 1234`).


## Activité 3-2 : calculatrice multi-thread
Communication par objets (`Operation` est `Serializable`). Un thread `ClientProcess` par client. Un compteur global partagé est incrémenté par la méthode `static synchronized incrementerCompteur()` de `CalculatriceServer`.

Lancement : `CalculatriceServer`, puis `Client` (ou `Client <IP_serveur> 1234`).
Ne pas lancer les deux serveurs en même temps (même port 1234).


## Tests en réseau local
1. Relever l'IP du serveur 
2. Autoriser le port 1234 dans le pare-feu.
3. Lancer les clients avec l'IP du serveur.

## Synthèse
Un thread par client permet au serveur de traiter plusieurs clients simultanément, chacun avec sa propre communication. Le compteur global est une ressource critique : sans synchronisation, deux threads pourraient lire la même valeur et une opération serait perdue. Avec `synchronized`, les valeurs affichées sont strictement croissantes et cohérentes, en local comme en réseau local.