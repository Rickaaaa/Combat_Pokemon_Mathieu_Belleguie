public class PokemonFeu extends Pokemon {
    public PokemonFeu(String nom, int hp, int atk) {
        super(nom, hp, atk);
    }

    @Override
    protected double obtenirMultiplicateur(Pokemon adversaire) {
        if (adversaire instanceof PokemonPlante) return 2.0;
        if (adversaire instanceof PokemonEau || adversaire instanceof PokemonFeu) return 0.5;
        return 1.0;
    }
}