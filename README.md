Tp1 - e2

Pour créer un projet éclipse à partir de ce repo, suivre les étapes indiquées de la première réponse de ce lien:

http://stackoverflow.com/questions/14769451/how-to-import-a-java-project-missing-project-settings-classpath-files-into-ec

Voici un résumé:

1. Faire sur que le dossier GLO3004E11TP2 n'existe pas déjà dans votre workspace eclipse.
2. Cloner le repo dans votre workspace eclipse, git clone https://github.com/alexdaube/GLO3004E11TP2.git
3. Créer un nouveau projet java dans eclipse en lui donnant simplement le nom du repo cloner(GLO3004E11TP2) et peser sur finish...
4. C'est tout! Ceci va faire en sorte que les fichiers appropriés soient créer.

*La raison pourquoi il n'est pas une bonne idée de tracker des fichiers comme des .classpath, .project, ou .settings avec git, c'est que ça crée plein de conflits pour rien... Vaut mieux éviter. Quand ça sera le temps de remttre, on aura qu'à zipper localement...


P.S.: J'ai bloqué le master, car dans mon expérience c'est pas une bonne chose de tous pushé dessus. Utiliser des branches pour faire du travail et faite des pull requests. 
