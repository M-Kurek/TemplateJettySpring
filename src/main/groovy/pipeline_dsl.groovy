javaposse.jobdsl.dsl.DslFactory factory = this

factory.pipelineJob("4th pipeline ${new Date().format('MM_dd_E')}") {
    definition {
        cpsScm {
            scm {
                git('https://github.com/M-Kurek/TemplateJettySpring.git', 'gradle_4_dsl')
                triggers {
                    scm("H/2 9-16 * * 1-5")
                }
            }
            scriptPath("src/main/groovy/pipelines_defs/pipeline_1.def") //default is : /Jenkinsfile
        }
    }
}
