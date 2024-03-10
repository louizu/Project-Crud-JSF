---------------------------------------------------------------------------

Projet du binome: Hachchane Fatima Zahra et Zaineb Majdi

---------------------------------------------------------------------------

Configuration de la base de données

Le fichier web.xml contient les informations de connexion à la base de  
données locale et à la base de données cloud Aiven. Les deux configurations
fonctionnent, mais veuillez noter que la base de données Aiven peut
présenter des performances plus lentes. Nous vous recommandons d'utiliser
une base de données locale, qui contient déjà une table prédéfinie (voir
employee.sql).

---------------------------------------------------------------------------

Fonctionnalités principales

Ajout, Modification, Suppression : L'application prend en charge les
opérations d'ajout, de modification et de suppression d'employés. En cas
d'erreur, des messages informatifs seront affichés à l'utilisateur en rouge.
Sinon, des messages de succès seront affichés en vert.

Contraintes d'email : L'adresse e-mail doit être unique, et le format doit
inclure un caractère '@'. L'application générera un message d'erreur si
ces conditions ne sont pas remplies.

Pagination : La table des employés est paginée, affichant 5 lignes par page.
Vous pouvez naviguer entre les pages en utilisant des boutons.

Mise à jour en temps réel : La page se met à jour automatiquement à chaque
action de l'utilisateur, assurant que les informations affichées sont
toujours à jour.

Internationalisation : L'application prend en charge deux langues, le
français et l'anglais. La langue par défaut de la page est celle de votre
système, mais vous pouvez basculer entre les deux en choisissant la langue
dans un menu déroulant.

---------------------------------------------------------------------------
