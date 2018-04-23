javaposse.jobdsl.dsl.DslFactory factory = this

factory.pipelineJob("ONE DSL create pipeline") {
    definition {
        cps {
            sandbox()
            script("""
pipeline {
    agent any
    options { 
      disableConcurrentBuilds() 
    }
    stages {
        stage('Baseline') {
            steps {
                shell("echo Baseline here")
            }
        }
        stage('Installation') {
            steps {
                shell("echo Installation here")
                script {
                    println "Step 1 : ${ new Date().format('HH:mm:ss.SSS')}"
                    sleep(10)
                    println "Step 2 : ${ new Date().format('HH:mm:ss.SSS')}"
                    sleep(10)
                    println "Step 3 : ${ new Date().format('HH:mm:ss.SSS')}"
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
