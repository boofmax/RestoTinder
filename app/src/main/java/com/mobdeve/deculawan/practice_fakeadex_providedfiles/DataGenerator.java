package com.mobdeve.deculawan.practice_fakeadex_providedfiles;

// your package here

import java.util.ArrayList;

public class DataGenerator {
    public static ArrayList<PokemonModel> loadData() {
        ArrayList<PokemonModel> data = new ArrayList<PokemonModel>();

        data.add(new PokemonModel("Brownisaur",
                "Fudge Sweet",
                "Professor Cinna’s Lab or the Cooking Dojo",
                "Sunlight is absorbed by its whipped cream and used to make the cherry on its back grow larger in size.",
                R.drawable.img1));
        data.add(new PokemonModel("Chocosaur",
                "Fudge Sweet",
                "Evolve Brownisaur",
                "The cherry on its back is known to be quite delicious but it is also heavy making it a useful blunt attack.",
                R.drawable.img2));
        data.add(new PokemonModel("Fudgasaur",
                "Fudge Sweet",
                "Evolve Chocosaur",
                "It grows many cherries. It can trap prey inside its whipped cream but it’s also a fun ride.",
                R.drawable.img3));
        data.add(new PokemonModel("Strawander",
                "Berry Sweet",
                "Professor Cinna’s Lab or the Cooking Dojo",
                "A sweet aroma is emitted from the strawberry tip of its tail which grows in size over time.",
                R.drawable.img4));
        data.add(new PokemonModel("Straweleon",
                "Berry Sweet",
                "Evolve Strawander",
                "Although the strawberry on its tail has grown heavier it can swing it against foes with relative ease.",
                R.drawable.img5));
        data.add(new PokemonModel("Strawizard",
                "Berry Sweet",
                "Evolve Strawmeleon",
                "Supposedly it has strawberry milk in place of saliva but it rarely drools unless its strawberry is bitten.",
                R.drawable.img6));
        data.add(new PokemonModel("Squirpie",
                "Apple Sweet",
                "Professor Cinna’s Lab or the Cooking Dojo",
                "Its head and limbs serve as filling for the pie on its belly. It can be eaten and it will grow back later.",
                R.drawable.img7));
        data.add(new PokemonModel("Tartortle",
                "Apple Sweet",
                "Evolve Squirpie",
                "Its apple pie has become very delicious. It becomes happy when its smell is complimented.",
                R.drawable.img8));
        data.add(new PokemonModel("Piestoise",
                "Apple Sweet",
                "Evolve Tartortle",
                "It has grown a pair of candy cane cannons which can fire very large and tasty cupcakes at its foes.",
                R.drawable.img9));
        data.add(new PokemonModel("Cottonat",
                "Cotton Sweet",
                "Flour Forest",
                "Its body is a ball of cotton candy. Swarms of it can unintentionally stick to and immobilize others.",
                R.drawable.img10));
        data.add(new PokemonModel("Cottomoth",
                "Moth Sweet",
                "Evolve Cottonat",
                "Its wings create a sweet aroma as it flies. It makes its nest out of sticky but delicious cotton candy.",
                R.drawable.img11));
        data.add(new PokemonModel("Spinacake",
                "Cake Sweet",
                "Flour Forest",
                "It hangs from and builds its web out of taffy. It also spins vanilla cakes for any unwilling guests.",
                R.drawable.img12));
        data.add(new PokemonModel("Ariacake",
                "Cake Sweet",
                "Evolve Spinacake",
                "Its legs are like birthday candles though they are never lit. Its cake-like body is filled with raspberry.",
                R.drawable.img13));
        data.add(new PokemonModel("Cookietot",
                "Gentlebug Sweet",
                "Flour Forest, Chocolate Cave",
                "Its body seems to contain cookie but it wears a raspberry outfit to improve its taste making it very attractive.",
                R.drawable.img14));
        data.add(new PokemonModel("Cookietune",
                "Candy Sweet",
                "Evolve Cookietot",
                "Its feelers have grown into candy canes. They have a raspberry-like taste to them but it is not the same.",
                R.drawable.img15));
        data.add(new PokemonModel("Chocowool",
                "Cotton Sweet",
                "Orange Road",
                "It very much loves hugs however one should be aware that it may leave a blob of chocolate on whatever it hugs.",
                R.drawable.img16));
        data.add(new PokemonModel("Chocofluff",
                "Cotton Sweet",
                "Evolve Chocowool",
                "It keeps its wool clean of any excess chocolate. Hugging it can still result in its cotton being left on the hugger.",
                R.drawable.img17));
        data.add(new PokemonModel("Candaros",
                "Sheep Sweet",
                "Evolve Chocofluff",
                "It has merged the chocolate and cotton candy to form its new body. It now favors licks over hugs.",
                R.drawable.img18));
        data.add(new PokemonModel("Rattatart",
                "Rat Sweet",
                "Apple Road, Cotton Crater, Pirate Fort",
                "The blueberry on its tail and its strawberry ears are rich in taste and used in tarts hence the name.",
                R.drawable.img19));
        data.add(new PokemonModel("Raticake",
                "Rat Sweet",
                "Cotton Crater, Pirate Fort, Evolve Rattatart",
                "The berries on its back constantly fall off as new ones grow. Other PokéSweets love to eat them.",
                R.drawable.img20));
        return data;
    }
}
