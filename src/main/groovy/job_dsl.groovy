/**
 * create 2 jobs and connect them in pipeline
 * problems : allowPipelineStart([*]) - nie startuje p-line
 */
javaposse.jobdsl.dsl.DslFactory factory = this

def stepBuild = "spring-mvn-build"
def stepDeploy = "spring-mvn-deploy"

factory.job(stepBuild) {
    deliveryPipelineConfiguration("Build")
    triggers {
        scm("H/2 9-16/2 * * 1-5")
    }
    scm { github("M-Kurek/TemplateJettySpring", "gradle_4_dsl") }
    steps {
        shell("gradle clean build")
    }

    publishers {
        //gradle version
        archiveJunit("build/test-results/test/*.xml")
        archiveArtifacts("build/libs/*.jar")

        /*
        mk-LATER : mvn version
        archiveJunit("target/surefire-reports/**")
        archiveArtifacts(["target/*.war", "target/*.jar"])
        */

        //manual trigger
        //buildPipelineTrigger()

        //(not manual) direct signal to streamline job exec
        downstreamParameterized {
            trigger(stepDeploy) { triggerWithNoParameters() }
        }
    }
}

factory.job(stepDeploy) {
    deliveryPipelineConfiguration("Deployment")

    steps {
        shell('echo deploying')
        shell('echo and deployed')
    }
}

factory.deliveryPipelineView("2nd DSL pipeline view") {
    pipelines {
        component("Create simple web app", stepBuild)
    }
    //no effect? allowPipelineStart(true)
    showChangeLog()
    allowRebuild()
    showDescription()
    showPromotions()
    showTotalBuildTime()
}
