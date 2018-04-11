javaposse.jobdsl.dsl.DslFactory factory = this

factory.pipelineJob("3rd DSL pipeline view") {
    definition {
        cps {
            sandbox()
            script("""
pipeline {
    agent any
    stages {
        stage('Baseline') {
            steps {
                shell("echo Baseline here")
            }
        }
        stage('Installation') {
            steps {
                shell("echo Installation here")
            }
        }

        stage('Post-install') {
            steps {
                shell("echo Post-install here")
            }
         }    
    }
}
      """.stripIndent())
        }
    }
}
