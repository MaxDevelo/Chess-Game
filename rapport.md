**RAPPORT DU RENDU 1**

- Utilisation d'une Factory pour les types de pions (une classe Piece puis sous-classe Roi, Reine, Cavalier...)
- Pour le plateau : class Square avec un attribut ligne colonne et Piece. Ligne et colonne nous permettent de connaitre la position d'une pièce et de déterminer où est ce qu'elle peut se déplacer.
- Pour les pièces, elles ont une méthode abstraite qui détermine comment elles se déplacent, puis c'est le plateau qui dit où est ce qu'elles peuvent aller en fonction de leur mode de déplacement.
- Le plateau est un singleton parce qu'on a un seul échiquier. -> Ne pas le mettre en singleton, passer le Board en paramètre.
- Changement du bool isWhite des pièces en une ENUM Color dans l'optique d'une variante du jeu à plus de 2 joueurs.
- Création d'un Controller qui va permettre de créer une nouvelle partie et générer le plateaux avec les pièces, d'obtenir le jeu, gère les déplacements, les changements de tour, donne le score.
- Lien de composition entre Game et Player car une partie ne peut pas exister sans joueurs.
- Lien d'association entre Player et Piece car le joueur va posséder des pièces capturées et que le joueur peut exister sans.
- Lien d'association entre Square et Piece car un Square peut posséder une pièce et peut exister sans.
- Lien d'association entre Controller et Game car il peut exister sans la partie.
- Lien d'association entre Controller et Board car il peut exister sans le Board.
- Lien d'association entre l'Enum des couleurs Player et Piece car ils peuvent exister sans.
- Lien de composition entre Board et Square car le plateau ne peut pas exister sans cases.
- Découppage de la fonction canMove en plusieurs petite sous fonction (up, down, right, left) pour simplifier la lisibilité du code.

Corriger déplacement gauche et haut de la tour.
Séparer les vues en sous classe ? (Panel joueur)

Mettre la multiplicité au bon endroit.
Remettre les up down dans toutes les pièces.
Retirer getBoard et getGame du controller.
Changer le displayScore du Controller en setScore (utiliser un Observer ?).
Passer par le controller uniquement pour modifier le model.

(Revoir la méthode de déplacement des pièces. Une pièce renvoie tous les square où elle peut théoriquement se déplacer, le board sélectionne les cases où elle peut vraiment aller. Revenir sur ce qu'on avait avant et enlever la dépendance circulaire. Transformer le square en juste une position ? (row/column). Calculer tous les déplacements possible dans la pièce et renvoyer si la case passé en paramètre est dans cette liste.) Déplacement Ok.
Séparer le code en petite fonction (haut, bas, gauche, droite).

Implémentation du mouvement des pièces pour:
- Pawn (Pion)
- King (Roi)
- Knight (Cavalier)

Les mouvements des pièces non implémentées:
- Bishop (Fou)
- Rook (Tour)
- Queen (Reine)

Mise à par de la création des pièces graphique dans createPieceUI afin
de pouvoir gérer le changement de pièces lors de la promotion?
Implémentation de la promotion du pion. _**On l'a fais en mode teste en changeant directement en cavalier**_.
Codage de la:
- Fonction idPromoted pour vérifier si on peut promovoir le Pion et que la pièce est un Pion.
- Fonction promotion afin de changer de type la pièce dans le tableau 2D de case.
- Dans la Vue, mise en place de selectPiecesPromotion pour choisir sa pièce.

Il faudra faire un petit ùenud éroulant pour choisir entre Cavalier, Reine, Fou et Tour.

Mise en place d'une enumeration des types de pièces et ajout de getScore comme méthode abstraite dans
la classe Piece.
