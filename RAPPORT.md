**RAPPORT DU RENDU 2**

- Mise en palce des 2 popups en cas échec et échec et mat du **ROI**
- Mise en place du menu de selectiond  epièce en cas de promotion
- Ajout dossier Game_Executable pour ajout le .jar
- Modification de la ise en forme dans la vue EndGameView
- On a changé le chemin des images (pièces) en les mettant dans un dossier resources et dans le code
- On a corrigé le fait que le Pion peut avancer de 2 cases au début. Avant, seul le premier pion pouvait avancer de 2 cases.
- Ajout des méthodes abstraites isFirstMove et setIsFirstMove afin de pouvoir gérer les bonus comme la prise en passant pour le Pion.
- Récupération et mise en palce d'une avriable dans Board afin de stocker la case de la dernière pièce déplacer afin de gérer la prise en passant du Pion.

Nous avons gérer la prise en passant pour le pion.

- Ajout de la couleur rouge sur la case quand il y a prise en passant pour indiquer qu'il peut attaquer le pion

- Correction du problème au niveau de l'échec et mate. Le joueur pouvait fermer le popup et conitnuer à jouer
- Lors de la promotion du Pion, nous avons sécurisé le fait que si le joueur ferme la boite de dialogue pour choisir
entre les 4 pièces, la boite de dialogue continue toujours à s'afficher tant qu'il a pas fait Ok.

- Mise à jour du README en ajoutant la notice d'installation (Windows, Linux, Mac)

##### A FAIRE
- ROQUE
- TESTER