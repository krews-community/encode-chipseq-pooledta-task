import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*
import step.*
import util.*
import java.nio.file.*
import util.CmdRunner


fun main(args: Array<String>) = Cli().main(args)

class Cli : CliktCommand() {
    private val taFiles: List<Path> by option("-taFiles", help = "path for tagAlign Files.")
        .path(exists = true).multiple().validate { require(it.isNotEmpty()) {"At least one path must be given"}}
    private val outputPrefix: String by option("-outputPrefix", help = "output file name prefix; defaults to 'output'").default("output")
    private val outDir by option("-outputDir", help = "path to output Directory")
        .path().required()

    override fun run() {
        val cmdRunner = DefaultCmdRunner()
        cmdRunner.runTask(taFiles, outDir,outputPrefix)
    }
}

/**
 * Runs pre-processing and bwa for raw input files
 *
 * @param taFiles pooledTa Input
 * @param outDir Output Path
 */
fun CmdRunner.runTask(taFiles:List<Path>, outDir:Path,outputPrefix:String) {

    pooledTa(taFiles,outDir,outputPrefix)
}