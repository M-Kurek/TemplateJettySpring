javaposse.jobdsl.dsl.DslFactory factory = this

factory.pipelineJob("ONE DSL creata pipeline") {
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
factory.pipelineJob("TWO DSL creata pipeline") {
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
    }
}
      """.stripIndent())
        }
    }
}
