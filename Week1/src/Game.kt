class Game {
    private val cardCollection: MutableSet<Card> = mutableSetOf()
    private val playerHand = mutableListOf<Card>()

    private fun createDeck(): MutableSet<Card> {
        val suitCollection = listOf('\u2663', '\u2660', '\u2666', '\u2665')
        val pipCollection = listOf(
            "2", "3", "4", "5",
            "6", "7", "8", "9",
            "10", "J", "Q", "K", "A"
        )

        for (suit in suitCollection) {
            for (pip in pipCollection) {
                val deckCard = Card(pip, suit)
                cardCollection.add(deckCard)
            }
        }
        return cardCollection
    }

    private fun dealHand(cardCollection: MutableSet<Card>): MutableList<Card> {
        while (playerHand.size < 2) {
            val card = cardCollection.random()
            playerHand.add(card)
            cardCollection.remove(card)
        }
        return playerHand
    }

    private fun evaluateHand(hand: MutableList<Card>): Int {
        var sumOfThePip = 0
        for (card in hand) {
            val (pip, _) = card
            val valueOfTheCard = when (pip) {
                "J", "Q", "K" -> 10
                "A" -> 11
                else -> pip.toInt()
            }
            sumOfThePip += valueOfTheCard
        }
        return sumOfThePip
    }

    private fun printResult(totalSum: Int, hand: MutableList<Card>) {
        println("Your hand was:")
        for (card in hand) {
            val (pip, suit) = card
            println("$pip$suit")
        }
        println("For a total of: $totalSum")
        println(if (totalSum != 21) "You Lose!" else "You Win!")
    }

    fun play() {
        createDeck()
        dealHand(cardCollection)
        val totalSum = evaluateHand(playerHand)
        printResult(totalSum, playerHand)
    }
}