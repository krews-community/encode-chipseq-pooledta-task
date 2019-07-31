import org.junit.jupiter.api.*
import step.*
import testutil.*
import testutil.cmdRunner
import testutil.setupTest
import java.nio.file.*
import org.assertj.core.api.Assertions.*

class PooledtaTests {
    @BeforeEach fun setup() = setupTest()
    @AfterEach fun cleanup() = cleanupTest()

     @Test fun `run pooledTa step singal file `() {
         var taFiles = mutableListOf<Path>()
         taFiles.add(TA1)
         cmdRunner.pooledTa(taFiles, testOutputDir,"mergedta")
         assertThat(testOutputDir.resolve("mergedta.pooled.tagAlign.gz")).exists()
         var l1 = cmdRunner.runCommand("zcat ${TA1} | wc -l ")!!.trim().toInt()
         var l2 = cmdRunner.runCommand("zcat ${testOutputDir.resolve("mergedta.pooled.tagAlign.gz")} | wc -l ")!!.trim().toInt()
         assertThat(l1).isEqualTo(l2)
    }
     @Test fun `run pooledTa step multiple file `() {
        var taFiles = mutableListOf<Path>()
        taFiles.add(TA1)
        taFiles.add(TA2)
        cmdRunner.pooledTa(taFiles, testOutputDir,"pooled_ta")
        assertThat(testOutputDir.resolve("pooled_ta.pooled.tagAlign.gz")).exists()
        var l1 = cmdRunner.runCommand("zcat ${TA1} | wc -l ")!!.trim().toInt()
        var l2 = cmdRunner.runCommand("zcat ${TA2} | wc -l ")!!.trim().toInt()
        var l3 = cmdRunner.runCommand("zcat ${testOutputDir.resolve("pooled_ta.pooled.tagAlign.gz")} | wc -l ")!!.trim().toInt()
        assertThat(l1+l2).isEqualTo(l3)
    }

}