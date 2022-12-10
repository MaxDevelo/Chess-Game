**RAPPORT DU RENDU 1**
- Utilisation d'une Factory pour les types de pions (une classe Piece puis sous-classe Roi, Reine, Cavalier...)
- Pour le plateau : class Square avec un attribut ligne colonne et Piece. Ligne et colonne nous permettent de connaitre la position d'une pièce et de déterminer où est-ce qu'elle peut se déplacer.
- Pour les pièces, elles ont une méthode abstraite qui détermine comment elles se déplacent, le plateau donne une case et la pièce dit si elle peut théoriquement y aller ou non.
- Changement du bool isWhite des pièces en une ENUM Color dans l'optique d'une variante du jeu à plus de 2 joueurs.
- Création d'un Controller qui va permettre de créer une nouvelle partie et générer le plateau avec les pièces, d'obtenir le jeu, gérer les déplacements, les changements de tour, donne le score.

- Lien de composition entre Game et Player, car une partie ne peut pas exister sans joueurs.
- Lien d'association entre Player et Piece car le joueur va posséder des pièces capturées et que le joueur peut exister sans.
- Lien d'association entre Square et Piece car un Square peut posséder une pièce et peut exister sans.
- Lien d'association entre Controller et Game car il peut exister sans la partie.
- Lien d'association entre Controller et Board car il peut exister sans le Board.
- Lien d'association entre l'Enum des couleurs Player et Piece car ils peuvent exister sans.
- Lien de composition entre Board et Square, car le plateau ne peut pas exister sans cases.
- Lien d'association entre Queen, Rook, Bishop et Square, car ils peuvent exister sans. Cela va permettre de se souvenir des cases vérifiées.

- Découppage de la fonction canMove en plusieurs petites sous fonctions (up, down, right, left) pour simplifier la lisibilité du code.

Implémentation du mouvement des pièces pour :
    - Pawn (Pion)
    - King (Roi)
    - Knight (Cavalier)
    - Bishop (Fou)
    - Rook (Tour)
    - Queen (Reine)

Implémentation de la promotion du pion. _**On l'a fait en mode test en changeant directement en cavalier**_.Implémentation de :
    - La fonction isPromoted pour vérifier si on peut promovoir le Pion et que la pièce est un Pion.
    - La fonction promotion afin de changer de type la pièce dans le tableau 2D de case.
    - Dans la Vue, mise en place de selectPiecesPromotion pour choisir sa pièce.

Mise en place d'une énumération des types de pièces et ajout de getScore comme méthode abstraite dansla classe Piece.
Dans la classe de Rook et Bishop, nous utilisons un tableau afin de stocker les cases après vérification, carla tour connait maintenant la case. Cela va permettre de voir les obstacle autour de celui-ci.

Création des pièces graphiques dans createPieceUI afinde pouvoir gérer le changement de pièces lors de la promotion ?
Il faudra faire un petit menu déroulant pour choisir entre Cavalier, Reine, Fou et Tour.
