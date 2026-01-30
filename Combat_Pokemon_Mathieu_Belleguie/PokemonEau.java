public class PokemonEau extends Pokemon {
    public PokemonEau(String nom, int hp, int atk) {
        super(nom, hp, atk);
    }

    @Override
    protected double obtenirMultiplicateur(Pokemon adversaire) {
        if (adversaire instanceof PokemonFeu) return 2.0;
        if (adversaire instanceof PokemonPlante || adversaire instanceof PokemonEau) return 0.5;
        return 1.0;
    }
}