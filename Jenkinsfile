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

    stage('Push to Docker hub') {
        sh 'docker tag portfolio:$BUILD_NUMBER yw7148/portfolio:$BUILD_NUMBER'
        sh 'docker push yw7148/portfolio:$BUILD_NUMBER';
    }

    stage ('Deploy') { 
        sh 'docker --context jenkins_was run -d --name portfolio --rm portfolio -p 9001:9001 \
            -e PROFILE=prod \
            --env-file /var/jenkins_home/secrets/youngwon/serverSecrets \
            yw7148/portfolio:$BUILD_NUMBER'
    }
}