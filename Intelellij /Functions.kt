import java.io.File
import java.lang.Exception

class Functions {
    companion object{

        fun makeSections(pathToRes: String, filename: String, n: Int){
            val files = File("$pathToRes/files/names.txt").bufferedReader().readLines()
            for (i in files.indices)
            {
                File("$pathToRes/files/" + files[i]+".txt").delete()
            }

            val f = File(filename).bufferedReader()
            val s = f.readLines()
            val names = File("$pathToRes/files/names.txt").bufferedWriter()
            for (i in s.indices step n)
            {
                try {
                    val file = File("$pathToRes/files/${s[i+n-1].substringAfter(';').substringBefore(';')}.txt").bufferedWriter()

                    names.write(s[i+n-1].substringAfter(';').substringBefore(';'))
                    names.newLine()

                    for (j in i until i + n) {
                        file.write(s[j])
                        file.newLine()
                    }
                    file.close()
                }
                catch (e: Exception){
                    val file = File("$pathToRes/files/${s[s.size-1].substringAfter(';').substringBefore(';')}.txt").bufferedWriter()

                    names.write(s[s.size-1].substringAfter(';').substringBefore(';'))
                    names.newLine()

                    for (j in i until s.size-1-i) {
                        file.write(s[j])
                        file.newLine()
                    }
                    file.close()
                }
            }
            f.close()
            names.close()
            println("Done")
        }



        fun order(filename: String){
            val f = File(filename).bufferedReader()
            val points = f.readLines()
            f.close()
            val f2 = File(filename).bufferedWriter()
            val a = points.toTypedArray()
            quick_sort(a,0,a.size-1)
            for(i in a.indices)
            {
                f2.write(a[i])
                f2.newLine()
            }
            f2.close()

        }

        private fun quick_sort(A: Array<String>, p: Int, r: Int) {
            if (p < r) {
                val q: Int = partition(A, p, r)
                quick_sort(A, p, q - 1)
                quick_sort(A, q + 1, r)

            }
        }

        private fun partition(A: Array<String>, p: Int, r: Int): Int {
            val x = A[r].substringAfter(';').substringBefore(';').toFloat()
            var i = p - 1
            for (j in p until r) {
                if (A[j].substringAfter(';').substringBefore(';').toFloat() <= x) {
                    i++
                    exchange(A, i, j)
                }
            }
            exchange(A, i + 1, r)
            return i + 1
        }

        private fun exchange(A: Array<String>, i: Int, j: Int) {
            val temp = A[i]
            A[i] = A[j]
            A[j] = temp
        }
        }




}
