node {
    stage('Git') {
        checkout scm
    }

    stage('Build & Push to Jenkins') {
        sh 'DOCKER_BUILDKIT=1 docker buildx build \
            --platform linux/arm64,linux/amd64 \
            --build-arg DB_URL="${DB_URL}" \
            --build-arg DB_USER="${DB_USER}" \
            --build-arg DB_PW="${DB_PW}" \
            --build-arg JWT_SECRET="${JWT_SECRET}" \
            -t yw7148/portfolio:latest --push .'
    }

    stage ('Deploy') { 
        sh 'docker --context jenkins_was1 rm -f portfolio || true'
        sh 'docker --context jenkins_was1 run --pull always -d --name portfolio -p 9001:9001 \
            -e PROFILE=prod \
            --env-file /var/jenkins_home/secrets/youngwon/serverSecrets \
            yw7148/portfolio:latest'
    }
}