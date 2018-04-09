/* helper setting for IntelliJ */
javaposse.jobdsl.dsl.DslFactory factory = this

def stepDeploy = "simple-mvn-boot-deploy"
def firstInPipeline = "simple-mvn-boot-build"


factory.job("dsl-one-job") {
    steps {
        shell("echo groovy : WOW !")
    }
}

factory.job(firstInPipeline) {
    deliveryPipelineConfiguration("Build")
    triggers {
        githubPush()
    }
    // coping project
    scm { github("M-Kurek/tmp4dsl") }
    //only colours
    wrappers { colorizeOutput() }
    steps {
        shell("echo pulled z git")
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
    scm { github("M-Kurek/tmp4dsl") }
    steps {
        shell('echo deploying')
        shell('echo and deployed')
    }
}

factory.deliveryPipelineView("my DSL pipeline view") {
    pipelines {
        component("Deployment", firstInPipeline)
    }
    allowPipelineStart()
    showChangeLog()
    allowRebuild()
    showDescription()
    showPromotions()
    showTotalBuildTime()
}
