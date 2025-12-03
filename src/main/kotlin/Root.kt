class Root

fun getText(file: String): String {
    return Root::class.java.getResource(file)!!.readText()
}

fun String.lines(stripBlank: Boolean = false): Sequence<String> = this.lineSequence().run {
    if (stripBlank) {
        filter { it.isNotBlank() }
    } else {
        this
    }
}

fun getLines(file: String, stripBlank: Boolean = false): Sequence<String> {
    return Root::class.java.getResource(file)!!.readText().lines(stripBlank)
}

fun <TIn, TOut> Sequence<TIn>.toColumns(count: Int, split: (TIn) -> List<TOut>): List<List<TOut>> {
    val lists = List<MutableList<TOut>>(count) { mutableListOf() }
    map(split).forEach {
        it.forEachIndexed { index, out ->
            lists[index].add(out)
        }
    }
    return lists
}