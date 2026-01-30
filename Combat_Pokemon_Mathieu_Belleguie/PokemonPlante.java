public class PokemonPlante extends Pokemon {
    public PokemonPlante(String nom, int hp, int atk) {
        super(nom, hp, atk);
    }

    @Override
    protected double obtenirMultiplicateur(Pokemon adversaire) {
        if (adversaire instanceof PokemonEau) return 2.0;
        if (adversaire instanceof PokemonFeu || adversaire instanceof PokemonPlante) return 0.5;
        return 1.0;
    }
}