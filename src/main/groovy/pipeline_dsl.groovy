javaposse.jobdsl.dsl.DslFactory factory = this

factory.pipelineJob("4th DSL create pipeline") {
    definition {
        cpsScm {
            scm {
                git('https://github.com/M-Kurek/TemplateJettySpring.git', 'gradle_4_dsl')
            }
            scriptPath("src/main/groovy/pipelines_defs/pipeline_def_2.txt")
        }
    }
}
