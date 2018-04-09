/* helper setting for IntelliJ */
javaposse.jobdsl.dsl.DslFactory factory = this

def stepBuild = "spring-mvn-build"
def stepDeploy = "spring-mvn-deploy"

factory.job(stepBuild) {
    deliveryPipelineConfiguration("Build")
    triggers {
        githubPush()
    }
    // coping project
    scm { github("M-Kurek/TemplateJettySpring", "gradle_4_dsl") }
    //only colours
    wrappers { colorizeOutput() }
    steps {
        shell("gradle clean build")
    }

    publishers {
        //gradle version
        archiveArtifacts("build/reports/tests/test/**")
        archiveArtifacts("build/libs/*.jar")

        /*
        mk-LATER : mvn version
        archiveArtifacts("target/surefire-reports/**")
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
    allowPipelineStart()
    showChangeLog()
    allowRebuild()
    showDescription()
    showPromotions()
    showTotalBuildTime()
}
