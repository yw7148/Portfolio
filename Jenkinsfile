node {
    stage('Git') {
        checkout scm
    }

    stage('Build') {
        sh 'DOCKER_BUILDKIT=1 docker build -t portfolio:$BUILD_NUMBER .'
    }

    stage ('Deploy') {
        sh 'docker rm -f $(docker ps -qa --filter name=portfolio)'
        sh 'docker run -d -p 9001:9001 --name portfolio -e PROFILE=prod --env-file /var/jenkins_home/secrets/youngwon/serverSecrets portfolio:$BUILD_NUMBER'
    }
}