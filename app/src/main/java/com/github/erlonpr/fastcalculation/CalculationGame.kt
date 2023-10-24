package com.github.erlonpr.fastcalculation

import kotlin.random.Random

enum class Operator {ADD, SUB, MUL, DIV}

// classe CalculationGame é responsável por gerar o jogo
// Ela tem um atributo rounds que especifica o número de rodadas do jogo.
class CalculationGame(private val  rounds:Int) {
    companion object {
        private const val INITIAL_VALUE = 1
        private const val FINAL_VALUE = 9
    }

    private var currentRound: Int = 0
    private val random = Random(System.currentTimeMillis())

    fun nextRound(): Round? {
        return if (currentRound < rounds) {
            var op1 = random.nextInt(INITIAL_VALUE, FINAL_VALUE + 1)
            var op2 = random.nextInt(INITIAL_VALUE, FINAL_VALUE + 1)

            // garante que a diferença entre os dois operandos seja de pelo menos 2
            // isso é feito para garantir que haja alternativas suficientes para a pergunta
            while (Math.abs(op1 - op2) < 2) {
                op2 = random.nextInt(INITIAL_VALUE, FINAL_VALUE + 1)
            }

            //  garante que op1 seja sempre maior ou igual a op2
            val x = op2
            op2 = op2.coerceAtMost(op1)
            op1 = x.coerceAtLeast(op1)

            val operator = Operator.values()[random.nextInt(4)]

            val answer: Int
            val question: String
            when (operator) {
                Operator.ADD -> {
                    answer = op1 + op2
                    question = "$op1 + $op2"
                }

                Operator.SUB -> {
                    answer = op1 - op2
                    question = "$op1 - $op2"
                }

                Operator.MUL -> {
                    answer = op1 * op2
                    question = "$op1 x $op2"
                }

                Operator.DIV -> {
                    answer = op1 / op2
                    question = "$op1 / $op2"
                }
            }
            currentRound++
            val altList = genAlternatives(op1, op2, answer) // método genAlternatives() gera três alternativas para a pergunta
            Round(question, answer, altList[0], altList[1], altList[2], currentRound)
        } else null
    }

    private fun genAlternatives(op1: Int, op2:Int, answer: Int):List<Int>{
        val operationSet = mutableSetOf(op1 - op2, op1+op2, op1 * op2, op1 / op2)
        val lowest = operationSet.min()
        val biggest = operationSet.max()

        val alternativeSet = mutableSetOf(answer)
        while (alternativeSet.size<3) {
            alternativeSet.add(random.nextInt(lowest, biggest + 1))
        }
        return alternativeSet.toList().shuffled()
    }

    data class Round (
        val question:String,
        val answer: Int,
        val alt1: Int,
        val alt2: Int,
        val alt3: Int,
        val round: Int
    )
}