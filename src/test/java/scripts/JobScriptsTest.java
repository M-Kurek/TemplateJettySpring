package scripts;

import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.dsl.MemoryJobManagement;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JobScriptsTest {
    private static final String SRC_DSL_GROOVY = "src/main/groovy/job_dsl.groovy";

    @Test
    public void compileSimpleScript() throws Exception {
        //given
        //Jenkins simulate
        MemoryJobManagement jm = new MemoryJobManagement();
        DslScriptLoader loader = new DslScriptLoader(jm);

        //when
        Path path = Paths.get(SRC_DSL_GROOVY);
        String script = new String(Files.readAllBytes(path));

        //then
        loader.runScript(script);
    }
}
