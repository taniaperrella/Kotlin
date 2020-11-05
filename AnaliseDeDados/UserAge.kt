//Desafio: Aplicar Programação Funcional para Análise de Dados Simples
//Decidimos coletar dados sobre a idade de nossos usuários.
//
//Neste desafio, você será apresentado a esses dados parcialmente defeituosos da idade do usuário, que se baseiam em quatro arquivos de entrada:
//
//// Alguns dados defeituosos com a idade de nossos usuários
//val data = mapOf (
//    "users1.csv" para listOf (32, 45, 17, -1, 34),
//    "users2.csv" para listOf (19, -1, 67, 22),
//    "users3.csv" para listOf (15, -124, 0, 12),
//    "users4.csv" para listOf (56, 32, 18, 44)
//)
//
//
//Aplique as funções que você aprendeu, bem como outras funções da biblioteca padrão do Kotlin para resolver as seguintes tarefas de análise de dados:
//
//Encontre a idade média dos usuários (use apenas idades válidas para o cálculo!)
//Extraia os nomes dos arquivos de entrada que contêm dados defeituosos
//Conte o número total de entradas com defeito em todos os arquivos de entrada


package basic

fun main(args: Array<String>) {
    val data = mapOf(
        "users1.csv" to listOf(32, 45, 17, -1, 34),
        "users2.csv" to listOf(19,-1, 67, 22),
        "users3.csv" to listOf(),
        "users4.csv" to listOf(56, 32, 18, 44)
    )

    val idadeMedia =  data.flatMap { it.value }
        .filter { it > 0}
        .average()

    val dadosIncompletos = data.filter { it.value.any{ it < 0} }
        .map { it.key }

    val qteDadosIncompletos = data.flatMap { it.value }
        .filter { it < 0 }
        .count()
}


package basic

fun main(args: Array<String>) {
    val data = mapOf(
        "users1.csv" to listOf(32, 45, 17, -1, 34),
        "users2.csv" to listOf(19,-1, 67, 22),
        "users3.csv" to listOf(),
        "users4.csv" to listOf(56, 32, 18, 44)
    )

    val idadeMedia =  data.flatMap { it.value }
        .filter { it > 0}
        .average()

    val dadosIncompletos = data.filter { it.value.any{ it < 0} }
        .map { it.key }

    val qteDadosIncompletos = data.flatMap { it.value }
        .filter { it < 0 }
        .count()
}