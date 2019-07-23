#!/usr/bin/env groovy
def createMessage() {
    def COLOR_MAP = ['SUCCESS': 'good', 'UNSTABLE': 'warning', 'FAILURE': 'danger', 'ABORTED': 'danger']
    def RESULT_MAP = ['SUCCESS': 'Passed', 'UNSTABLE': 'Unstable', 'FAILURE': 'Failed', 'ABORTED': 'Aborted']
    def buildTime = "Build completed, :stopwatch: *Took:* ${currentBuild.durationString}".replace(' and counting', '')
    def timestamp = new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone('UTC'))
    def payload = [:]
    payload['username'] = "Jenkins"
    payload['icon_url'] = 'https://i.imgur.com/gLAhVPC.png'
    payload['attachments'] = []
    payload['attachments'][0] = [:]
    payload['attachments'][0]['pretext'] = "Jenkins Job: *${JOB_NAME}*:"
    payload['attachments'][0]['color'] = "${COLOR_MAP[currentBuild.currentResult]}"
    payload['attachments'][0]['fields'] = []
    
    if (env.PROJECT) {
    payload['attachments'][0]['fields'].add([
      'title': 'Project name :computer: :', 
      'value': "`${PROJECT}`", 
      "short": true
    ])
    }
    if (env.SERVICE) {
    payload['attachments'][0]['fields'].add([
      'title': 'Project service :pushpin: :', 
      'value': "`${SERVICE}`", 
      "short": true
    ])        
    }
    if (env.ENV) {
    payload['attachments'][0]['fields'].add([
      'title': 'Project environment  :computer: :', 
      'value': "`${ENV}`", 
      "short": true
    ])
    }
    if (env.GIT_BRANCH) {
    payload['attachments'][0]['fields'].add([
      'title': 'Project branch :pushpin::', 
      'value': "`${GIT_BRANCH}`", 
      "short": true
    ])
    }
    if (env.GIT_COMMIT || env.GIT_URL) {
    def repoURL =  "${GIT_URL}".replace(".git", "")
    def shortCommit = "${GIT_COMMIT}".substring(0, 7)
    payload['attachments'][0]['fields'].add([
      'title': 'Commit :guitar: :', 
      'value': "${repoURL}/commit/${GIT_COMMIT}", 
      "short": true
    ])
    }
    if (env.GIT_COMMITTER_NAME || env.GIT_COMMITTER_EMAIL) {
    payload['attachments'][0]['fields'].add([
      'title': 'Commiter :busts_in_silhouette: :', 
      'value': "`${GIT_COMMITTER_NAME}` <mailto:${GIT_COMMITTER_EMAIL}|${GIT_COMMITTER_EMAIL}>", 
      "short": true
    ])
    }
    if (env.BUILD_USER)
    payload['attachments'][0]['fields'].add([
      'title': 'Started By :relieved: :', 
      'value': "Jenkins user: ${BUILD_USER}", 
      "short": true
    ])
    payload['attachments'][0]['footer'] = "${buildTime}"
    payload['attachments'][0]['ts'] = "${System.currentTimeMillis()/1000}"
    return payload
}
