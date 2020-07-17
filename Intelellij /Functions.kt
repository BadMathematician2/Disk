import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import kotlin.concurrent.thread

class Functions {
    companion object{

        fun makeSections(pathToRes: String, filename: String, n: Int){
            order(filename)
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
            shorte(a)
            for(i in a.indices)
            {
                f2.write(a[i])
                f2.newLine()
            }
            f2.close()

        }


        private fun shorte(arr: Array<String>)
        {
            var sorted = false
            var temp: String
            while (!sorted)
            {
                sorted = true
                for (i in 0 until arr.size - 1)
                {
                    if (arr[i].substringBefore(';').toDouble() > arr[i+1].substringBefore(';').toDouble())
                    {
                        temp = arr[i]
                        arr[i] = arr[i+1]
                        arr[i+1] = temp
                        sorted = false
                    }
                }
            }
        }
        }




}