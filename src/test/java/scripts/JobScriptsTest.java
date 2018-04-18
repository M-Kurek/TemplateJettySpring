package scripts;

import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.dsl.MemoryJobManagement;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobScriptsTest {

    private static List<Path> scripts = new ArrayList<Path>();
	static {
        Path testFilesFolder = Paths.get("src/main/groovy/");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(testFilesFolder, "*.groovy")) {
            for (Path file : stream) {
                if (!scripts.contains(file))
                    scripts.add(file);
            }
        } catch (IOException | DirectoryIteratorException x) {
            ;
        }
    }
	//TODO move to groovy e.g. https://www.javacodegeeks.com/2015/04/short-on-time-switch-to-groovy-for-unit-testing.html

    @Test
    public void compileSimpleScript() throws Exception {
        //given
        //Jenkins simulate
        MemoryJobManagement jm = new MemoryJobManagement();
        DslScriptLoader loader = new DslScriptLoader(jm);

        //then
        for (Path scriptPath : scripts) {
            loader.runScript(new String(Files.readAllBytes(scriptPath)));
        }
    }}
