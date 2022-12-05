- Utilisation d'une Factory pour les types de pions (une classe Piece puis sous-classe Roi, Reine, Cavalier...)
- Pour le plateau : class Square avec un attribut ligne colonne et Piece. Ligne et colonne nous permettent de connaitre la position d'une pièce et de déterminer où est ce qu'elle peut se déplacer.
- Pour les pièces, elles ont une méthode abstraite qui détermine comment elles se déplacent, puis c'est le plateau qui dit où est ce qu'elles peuvent aller en fonction de leur mode de déplacement.
- Utilisation d'un système de points cardinaux (Nord, Sud, Est, Ouest) pour le déplacement des pions. Est, Ouest pour changement de colonne, Nord, Sud pour changement de ligne.
- Le plateau est un singleton parce qu'on a un seul échiquier. -> Ne pas le mettre en singleton, passer le Board en paramètre.
- Changement du bool isWhite des pièces en une ENUM Color dans l'optique d'une variante du jeu à plus de 2 joueurs.
- La Class Board est un model.
- Création d'un Controller qui va permettre de créer une nouvelle partie et générer le plateaux avec les pièces, d'obtenir le jeu, gère les déplacements, les changements de tour, donne le score.

Corriger déplacement gauche et haut de la tour.
Vérifier les liens d'association/composition et expliquer dans le rapport pourquoi l'un et pas l'autre
Supprimer la deuxième flèche entre Game et Player
Lien d'association avec l'enum color
Mettre à jour l'UML (color pion)
(Revoir la méthode de déplacement des pièces. Un pièce renvoie tous les square où elle peut théoriquement se déplacer, 
le board sélectionne les cases où elle peut vraiment aller. Revenir sur ce qu'on avait avant et enlever la dépendance circulaire.
Transformer le square en juste une position ? (row/column). Calculer tous les déplacements possible dans la pièce et renvoyer si
la case passé en paramètre est dans cette liste.) Déplacement Ok.