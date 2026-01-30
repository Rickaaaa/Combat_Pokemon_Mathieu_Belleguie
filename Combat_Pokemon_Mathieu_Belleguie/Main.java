import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Préparation du jeu : on remplit la liste des Pokémon disponibles
        List<Pokemon> pokedex = initialiserPokedex();
        Random rand = new Random();
        
        // Sélection aléatoire des deux combattants dans la liste
        Pokemon p1 = pokedex.get(rand.nextInt(pokedex.size()));
        Pokemon p2 = pokedex.get(rand.nextInt(pokedex.size()));

        // Sécurité : On boucle tant que les deux Pokémon sont identiques
        // pour éviter qu'un Pokémon ne se batte contre lui-même.
        while (p1 == p2) {
            p2 = pokedex.get(rand.nextInt(pokedex.size()));
        }

        // Présentation du combat
        System.out.println("=========================================");
        System.out.println(">>> VOICI NOTRE COMBAT DU JOUR ! <<<");
        System.out.println("=========================================");
        
        // Affichage des stats avant le début du match
        // J'utilise les getters car les attributs sont privés (Encapsulation)
        System.out.println("A ma gauche : " + p1.getNom() + " (" + p1.getClass().getSimpleName() + ") \t| (HP):" + p1.getHp() + " [ATK]:" + p1.getAtk());
        System.out.println("A ma droite : " + p2.getNom() + " (" + p2.getClass().getSimpleName() + ") \t| (HP):" + p2.getHp() + " [ATK]:" + p2.getAtk());
        
        System.out.println("=========================================\n");

        // Lancement de la boucle de jeu
        lancerCombat(p1, p2);
    }

    /**
     * Méthode pour initialiser une liste variée de Pokémon.
     * J'utilise une ArrayList pour pouvoir ajouter facilement des nouveaux Pokémon plus tard.
     */
    public static List<Pokemon> initialiserPokedex() {
        List<Pokemon> liste = new ArrayList<>();
        
        // Ajout des Pokémon Feu
        liste.add(new PokemonFeu("Dracaufeu", 150, 40));
        liste.add(new PokemonFeu("Arcanin", 130, 35));
        liste.add(new PokemonFeu("Salameche", 100, 20));
        
        // Ajout des Pokémon Eau
        liste.add(new PokemonEau("Tortank", 160, 30));
        liste.add(new PokemonEau("Leviator", 140, 45));
        liste.add(new PokemonEau("Carapuce", 100, 20));
        
        // Ajout des Pokémon Plante
        liste.add(new PokemonPlante("Florizarre", 160, 35));
        liste.add(new PokemonPlante("Noadkoko", 120, 40));
        liste.add(new PokemonPlante("Bulbizarre", 100, 20));
        
        // Ajout des types Normal
        liste.add(new PokemonNormal("Ronflex", 200, 25));
        liste.add(new PokemonNormal("Mewtwo", 180, 50));
        
        return liste;
    }

    /**
     * Gère la logique du combat tour par tour.
     * S'arrête dès qu'un Pokémon tombe KO.
     */
    public static void lancerCombat(Pokemon p1, Pokemon p2) {
        int tour = 1;

        // Tant qu'aucun des deux n'est KO (hp <= 0), le combat continue
        while (!p1.isDead() && !p2.isDead()) {
            System.out.println("--- Tour " + tour + " ---");
            
            // Le premier attaque
            p1.attaquer(p2);
            // Si le deuxième meurt sur le coup, on arrête la boucle tout de suite
            if (p2.isDead()) break;

            // Riposte du deuxième
            p2.attaquer(p1);
            // Si le premier meurt, on arrête aussi
            if (p1.isDead()) break;

            System.out.println();
            tour++;
            
            // Petite pause de 500ms pour rendre l'affichage plus lisible
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }

        // Fin du combat : annonce du vainqueur
        System.out.println("\n=========================================");
        System.out.println(">>> FIN DU COMBAT <<<");
        System.out.println("=========================================");
        
        // On vérifie qui est mort pour déclarer l'autre vainqueur
        if (p1.isDead()) {
            System.out.println(">>> VAINQUEUR : " + p2.getNom().toUpperCase() + " !!! (HP restants: " + p2.getHp() + ")");
        } else {
            System.out.println(">>> VAINQUEUR : " + p1.getNom().toUpperCase() + " !!! (HP restants: " + p1.getHp() + ")");
        }
    }
}