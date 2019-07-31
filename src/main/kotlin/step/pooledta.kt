package step
import mu.KotlinLogging
import util.*
import java.nio.file.*
import util.CmdRunner
private val log = KotlinLogging.logger {}

fun CmdRunner.pooledTa(taFiles:List<Path>,outDir:Path,outputPrefix:String) {
    log.info { "Make output Diretory" }
    Files.createDirectories(outDir)
    val prefix = outDir.resolve(outputPrefix)
    val pooled_ta = "${prefix}.pooled.tagAlign.gz"
    val tf  = taFiles.map { it.toString() }
    val cmd =  "zcat -f ${tf.joinToString(" ") } |  gzip > ${pooled_ta}"
    this.run(cmd)

}
