public abstract class Pokemon {

 
    // 1. ATTRIBUTS EN PRIVATE (Le "Coffre-fort")
    // Seule cette classe a le droit d'y toucher directement.
    // Les autres doivent passer par les méthodes (Getters/Setters).
    private String nom;
    private int hp;
    private int hpMax; // Pour se souvenir des PV max lors du soin
    private int atk;

    // CONSTRUCTEUR
    public Pokemon(String nom, int hp, int atk) {
        // On valide les données dès la création pour éviter les bugs plus tard
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Erreur : Le nom est vide !");
        }
        if (hp <= 0) {
            throw new IllegalArgumentException("Erreur : Les HP doivent être > 0 !");
        }
        if (atk < 0) {
            throw new IllegalArgumentException("Erreur : L'attaque ne peut pas être négative !");
        }

        this.nom = nom;
        this.hp = hp;
        this.hpMax = hp;
        this.atk = atk;
    }

    // 2. LES GETTERS (Accès en lecture seule)
    
    public String getNom() {
        return nom;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getHpMax() {
        return hpMax;
    }

    // 3. LES SETTERS (Accès en écriture sécurisé)

    public void setHp(int hp) {
        this.hp = hp;
        // Petite sécurité : on empêche les PV de descendre en dessous de 0
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void setAtk(int atk) {
        // On empêche de donner une attaque négative
        if (atk < 0) {
             throw new IllegalArgumentException("L'attaque ne peut pas être négative");
        }
        this.atk = atk;
    }

    // 4. MÉTHODES DE COMBAT

    public boolean isDead() {
        return this.hp <= 0; 
    }

    public void soigner() {
        this.hp = this.hpMax;
    }

    public void attaquer(Pokemon adversaire) {
        // Vérifications de base avant de taper
        if (adversaire == null) return;
        if (this.isDead() || adversaire.isDead()) return;

        // 1. On récupère le multiplicateur (x2, x0.5...) via la méthode abstraite
        double multiplicateur = obtenirMultiplicateur(adversaire);
        
        // 2. On calcule les dégâts
        int degats = (int) (this.atk * multiplicateur);

        // 3. On applique les dégâts sur l'adversaire via sa méthode
        adversaire.recevoirDegats(degats);

        // 4. Affichage propre du déroulement
        // J'utilise les getters (adversaire.getNom()) pour respecter l'encapsulation
        System.out.printf("%s attaque %s : ATK=%d, x%.1f => %d degats. (%s HP=%d)%n", 
            this.nom, 
            adversaire.getNom(), 
            this.atk, 
            multiplicateur, 
            degats, 
            adversaire.getNom(), 
            adversaire.getHp()   
        );

        if (adversaire.isDead()) {
            System.out.println(">>> " + adversaire.getNom() + " est KO !");
        }
    }

    // Méthode interne pour gérer la perte de PV
    protected void recevoirDegats(int degats) {
        this.hp -= degats;
        if (this.hp < 0) this.hp = 0;
    }

    // Chaque classe fille (Feu, Eau...) devra expliquer ses forces/faiblesses ici
    protected abstract double obtenirMultiplicateur(Pokemon adversaire);

    @Override
    public String toString() {
        // On affiche l'état actuel du Pokémon
        return String.format("%s (%s) HP:%d ATK:%d", 
            getNom(), this.getClass().getSimpleName(), getHp(), getAtk());
    }
}