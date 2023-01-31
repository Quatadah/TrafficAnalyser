# TrafficAnalyser
## Auteurs : 
`Quatadah NASDAMI`
`Youssef ELAMOUD`

Ce projet a été réalisé pour visualiser les données récoltées par des capteurs de circulation. Les données sont stockées sur Hadoop Map Reduce et un dashboard a été créé pour afficher les données sous forme de graphiques et de tableaux pour une analyse plus approfondie.

## Technologies utilisées
- Hadoop
- Apache Hbase
- React 
- Tailwindcss
- React-Leaflet
- Material UI
- Node js

## Nettoyage et analyse de données

Les données collectées par les capteurs de circulation sont stockées dans un système de stockage distribué Hadoop. Ce système permet de traiter de grandes quantités de données en parallèle sur plusieurs nœuds.

Lors du traitement des données, le modèle MapReduce est utilisé pour distribuer les tâches de traitement sur les différents nœuds. Le modèle MapReduce consiste en deux étapes: la première étape de Map transforme les données en un format utilisable, tandis que la seconde étape de Reduce agrège les données en une sortie consolidée.

Avant d'être stockées dans Hadoop, les données ont été nettoyées et normalisées pour éliminer les données erronées et garantir que les données sont cohérentes. Ce processus de nettoyage de données est important pour garantir que les analyses produites par le dashboard sont précises et fiables.

## Dashboard
- Visualisation de la vitesse moyenne par type de véhicules
- Analyse de la moyenne mensuelle de présence de type de véhicules par heure et par capteur
- Analyse de la moyenne mensuelle de présence de type de véhicules par jour et par capteur
- Observation par poste de capteur
- Nombre d'entrées/sorties par type de véhicules et par date

## Fonctionnalités supplémentaires
Le dashboard comprend également une fonctionnalité de sélection qui permet aux utilisateurs de choisir un poste de capteur spécifique et d'afficher les données pour ce poste uniquement. De la même manière, le dashboard comprend une fonctionnalité de sélection de date qui permet aux utilisateurs de choisir une date spécifique et de visualiser les données pour ce jour uniquement.

## Installation
```bash
  git clone github.com/Quatadah/TrafficAnalyser.git
  cd TrafficAnalyser/front
  npm i
  npm run start
```
## Commande pour lancer le jar 
```bash
HADOOP_CLASSPATH=/etc/hbase/conf:$(hbase mapredcp) yarn jar ProjectMaven.jar /user/auber/data_ple/citytraffic/ResultatCSV/ outPutCleanedData ouputAnalyse1 ouputAnalyse2 ouputAnalyse3 ouputAnalyse4 ouputAnalyse5
```

## Résultat final :
![ta-dashboard](https://user-images.githubusercontent.com/73450837/215629079-aa5cbe05-b856-43ea-9c61-0e08d59deb6f.png)


