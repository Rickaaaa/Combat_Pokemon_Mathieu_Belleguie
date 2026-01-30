public class PokemonNormal extends Pokemon {
    public PokemonNormal(String nom, int hp, int atk) {
        super(nom, hp, atk);
    }

    @Override
    protected double obtenirMultiplicateur(Pokemon adversaire) {
        return 1.0;
    }
}