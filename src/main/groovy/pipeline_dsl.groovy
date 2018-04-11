javaposse.jobdsl.dsl.DslFactory factory = this

factory.pipelineJob("4th DSL create pipeline") {
    definition {
        cpsScm {
            scm {
                git('https://github.com/jenkinsci/job-dsl-plugin.git')
            }
            scriptPath("src/main/groovy/pipelines_defs/pipeline_def_1.txt")
        }
    }
}
