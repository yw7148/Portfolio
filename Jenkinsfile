node {
    stage('Git') {
        checkout scm
    }

    stage('Build') {
        sh 'DOCKER_BUILDKIT=1 docker build \
            --build-arg DB_URL="${DB_URL}" \
            --build-arg DB_USER="${DB_USER}" \
            --build-arg DB_PW="${DB_PW}" \
            --build-arg JWT_SECRET="${JWT_SECRET}" \
            -t portfolio:$BUILD_NUMBER .'
    }

    stage ('Deploy') {
        sh 'docker rm -f $(docker ps -qa --filter name=portfolio)'
        sh 'docker run -d -p 9001:9001 --name portfolio -e PROFILE=prod portfolio:$BUILD_NUMBER'
    }
}