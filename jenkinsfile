#!groovy
node {
  def flow = load "/scripts/util.groovy"
  flow.init()

  flow.wstage("Images", {
    sh 'docker-compose build'
  })

  flow.wstage("Build/Test", {
    sh 'docker-compose run --rm test'
  }, {
    sh 'docker-compose down -v'
  })

  flow.end()
}