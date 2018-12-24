#!groovy
ctx = [:]

node {

    wstage("Checkout", {

        ctx.network_id = "${env.JOB_NAME.replaceAll("\\s","")}_${env.BUILD_NUMBER.trim()}"
        println "net id ${ctx.network_id}"
        sh "docker network create ${ctx.network_id}"

        ctx.workspace_path = '/' + env.WORKSPACE.split('/').last()
        println "work space ${ctx.workspace_path}"
        workspace_mount = env.WORKSPACE.take(env.WORKSPACE.lastIndexOf('/'))
        env.MY_WORKSPACE = sh(returnStdout: true, script: "docker inspect \$(hostname) | jq -r '.[] | .Mounts[] | select(.Destination==\"${workspace_mount}\") | .Source'").trim()
        env.WORKSPACE_PATH = ctx.workspace_path

        checkout scm

    })

    wstage("Images", {
        sh 'docker-compose build'
    })

    wstage("Build/Test", {
        sh 'docker-compose run --rm test'
    }, {
        sh 'docker-compose down -v'
    })

    end()
}

def wstage(String name, Closure block, Closure end = null) {
    stage(name) {
        try {
            block.call()
        } catch (err) {
            println err
            throw err
        } finally {
            if (end) {
                end.call()
            }
        }
    }
}

def end() {
    stage("End") {
        sh "docker network rm ${ctx.network_id}"
    }
}

