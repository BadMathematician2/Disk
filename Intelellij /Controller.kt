fun main(args: Array<String>) {
        val command = args[0]
        val path = args[1]
    when {
        command.substringBefore('=') == "--order" -> Functions.order(command.substringAfter('='))
        command.substringBefore('=') == "--update" -> Functions.makeSections(path,command.substringAfter('='), 5000)
        else -> println("Sorry, i can`t do what you want. Please type another command")
    }
}

