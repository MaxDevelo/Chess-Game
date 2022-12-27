**RAPPORT DU RENDU 2**

- Mise en place des 2 popups en cas échec **et** échec et mat du **ROI**
- Mise en place du menu de selection des pièces en cas de promotion
- Ajout du dossier commé Game_Executable pour ajouter le .jar
- Modification de la mise en forme dans la vue EndGameView
- On a changé le chemin des images (pièces) en les mettant dans un dossier resources et dans le code
- On a corrigé le fait que le Pion peut avancer de 2 cases au début. Avant, seul le premier Pion pouvait avancer de 2 cases.
- Ajout des méthodes abstraites isFirstMove et setIsFirstMove afin de pouvoir gérer les bonus comme la prise en passant pour le Pion.
- Récupération et mise en place d'une variable dans Board afin de stocker la case de la dernière pièce déplacer afin de gérer la prise en passant du Pion.
- Ajout de la couleur rouge sur la case quand il y a prise en passant pour indiquer qu'il peut attaquer le pion
- Correction du problème au niveau de l'échec et mate. Le joueur pouvait fermer le popup et continuer à jouer
- Lors de la promotion du Pion, nous avons sécurisé le fait que si le joueur ferme la boite de dialogue pour choisir
entre les 4 pièces, la boite de dialogue continue toujours à s'afficher tant qu'il a pas fait Ok.
- Nous avons ajouté la fonction canCastle dans la classe Board afin de vérifier si le Roi peut roquer. Dans cette fonction, lorsque le Roi peut roquer,
on ajoute la case où il peut roquer dans les cases valides.
- Nous avons décidé d'enlever l'appelle de la fonction qui clear la liste des cases valide (clearValidSquare()) dans la fonction où l'on ajoute les cases valides en la mettant à l'endroit où on l'appelle car
lorsque l'on voulait gérer le Roque du Roi, la liste des cases valides étaient chaque fois Clear. Car on en avait besoin lorsque l'on vérifiait si le Roi était en Echec.
- Ajout de la fonction verifyLimitBoard afin de vérifier si la position n'est pas en dehors du plateau afin de gérer les erreurs.
- Gestion de la partie Nulle (Quand un des joueurs n'a plus la possibilitée de placer ses pièce, sans prendre en compte le Roi)
- Dans la méthode moveAt, nous avons ajouté dans la condition qui permet de vérifier si il y a prise passant, que la colonne de la case actuelle de la pièce n'est pas la meme que la case où l'on souhaite aller. En effet
si on ne fait pas sa et que le joueur ne veut pas faire la prise en passant en avançant juste d'une case. Cela comptait comme si il avait fait une prise en passant.
- Mise à jour du README en ajoutant la notice d'installation (Windows, Linux, Mac)
- On a réussi à mettre le ficheir plantUML en svg (car dans la version 1, on ne savait plus comment faire...)

Ce qu'on a pas fait dans ce projet, c'est la détection des pièces clouées, car on avait du mal à comprendre et à mettre en place.