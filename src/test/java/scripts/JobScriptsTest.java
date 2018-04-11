package scripts;

import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.dsl.MemoryJobManagement;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JobScriptsTest {
	private Path[] scriptPaths = {
            Paths.get("src/main/groovy/job_dsl.groovy"),
            Paths.get("src/main/groovy/pipeline_dsl.groovy")
    };

    @Test
    public void compileSimpleScript() throws Exception {
        //given
        //Jenkins simulate
        MemoryJobManagement jm = new MemoryJobManagement();
        DslScriptLoader loader = new DslScriptLoader(jm);

        //then
        for (Path scriptPath : scriptPaths) {
            loader.runScript(new String(Files.readAllBytes(scriptPath)));
        }
    }}
