javaposse.jobdsl.dsl.DslFactory factory = this

factory.pipelineJob("ONE DSL create pipeline") {
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
                timeout(time:10, unit:'SECONDS') {
                    input message:'some delay 1'
                }
                timeout(time:10, unit:'SECONDS') {
                    input message:'some delay 2'
                }
                shell("echo Installation finished")
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
factory.pipelineJob("TWO DSL create pipeline") {
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
